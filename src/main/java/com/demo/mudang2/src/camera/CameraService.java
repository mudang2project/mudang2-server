package com.demo.mudang2.src.camera;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.camera.model.PostHeadCountRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;

@Service

public class CameraService {
    private final CameraDao cameraDao;
    private final CameraProvider cameraProvider;

    @Autowired
    public CameraService(CameraDao cameraDao, CameraProvider cameraProvider) {
        this.cameraDao = cameraDao;
        this.cameraProvider = cameraProvider;
    }

    public PostHeadCountRes createHeadCount(int headCount) throws BaseException {
        try{
            int idx = cameraDao.createHeadCount(headCount);
            return new PostHeadCountRes(idx);

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
