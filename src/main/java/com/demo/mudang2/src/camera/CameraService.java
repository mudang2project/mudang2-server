package com.demo.mudang2.src.camera;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.camera.model.PostHeadCountRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;
import static com.demo.mudang2.config.BaseResponseStatus.INSERT_FAIL_HEADCOUNT;

@Service

public class CameraService {
    private final CameraDao cameraDao;
    private final CameraProvider cameraProvider;

    @Autowired
    public CameraService(CameraDao cameraDao, CameraProvider cameraProvider) {
        this.cameraDao = cameraDao;
        this.cameraProvider = cameraProvider;
    }

    //response 유

//    public PostHeadCountRes createHeadCount(int headCount) throws BaseException {
//        try{
//            int idx = cameraDao.createHeadCount(headCount);
//            return new PostHeadCountRes(idx);
//
//        }catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

    //response 무
    public void createHeadCount(int headCount) throws BaseException {
        try{
            int result = cameraDao.createHeadCount(headCount);
            if(result == 0) {
                throw new BaseException(INSERT_FAIL_HEADCOUNT);
            }

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
