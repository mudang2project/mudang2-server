package com.demo.mudang2.src.camera;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.camera.model.GetHeadCount;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.CAMERA_HEADCOUNT_DELAY;
import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CameraProvider {
    private final CameraDao cameraDao;

    @Autowired //readme 참고
    public CameraProvider(CameraDao cameraDao) {
        this.cameraDao = cameraDao;
    }

    public GetHeadCount getHeadCount() throws BaseException {
        try {
            int cnt = cameraDao.getCountCamera();
            if(cnt == 0) {
                int newSql = cameraDao.insertHeadCount();
                GetHeadCount getHeadCountRes = cameraDao.getHeadCount();
                return getHeadCountRes;
            }
            else {
                GetHeadCount getHeadCountRes = cameraDao.getHeadCount();
                return getHeadCountRes;
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
