package com.demo.mudang2.src.admin;


import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.admin.model.*;
import com.demo.mudang2.src.camera.model.GetHeadCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;


@Service
public class AdminProvider {

    private final AdminDao adminDao;

    @Autowired //readme 참고
    public AdminProvider(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    //디바이스 onoff 조회
    public List<GetPower> getPower() throws BaseException {
        try {
            List<GetPower> getPowerRes = adminDao.getPower();
            return getPowerRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //gps 최근 데이터 조회
    public List<GetRecentGps> getRecentGps() throws BaseException {
        try {
            List<GetRecentGps> getRecentGpsRes = adminDao.getGpsList();
            return getRecentGpsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetRecentCamera> getRecentCamera() throws BaseException {
        try {
            List<GetRecentCamera> getRecentCameraRes = adminDao.getCameraList();
            return getRecentCameraRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //데이터 사용량 확인 조회
    public List<GetDataCheck> getDataCheck() throws BaseException {
        try {
            List<GetDataCheck> getDataCheckRes = adminDao.getDataCheck();
            return getDataCheckRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}