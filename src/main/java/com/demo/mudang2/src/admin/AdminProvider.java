package com.demo.mudang2.src.admin;


import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.admin.model.*;
import com.demo.mudang2.src.admin.model.GetDataCheck;
import com.demo.mudang2.src.admin.model.GetPower;
import com.demo.mudang2.src.admin.model.GetRecentGps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;


@Service
@RequiredArgsConstructor
public class AdminProvider {

    private final AdminDao adminDao;

    /**
     * 로그인 - 비밀번호 가져오기
     */

    public String getPassword() throws BaseException {
        try {
            String password = adminDao.getPassword();
            return password;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /**
     * 디바이스 on/off 조회
     */

    public List<GetPower> getPower() throws BaseException {
        try {
            List<GetPower> getPowerRes = adminDao.getPower();
            return getPowerRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * gps 최근 데이터 조회
     */

    public List<GetRecentGps> getRecentGps() throws BaseException {
        try {
            List<GetRecentGps> getRecentGpsRes = adminDao.getGpsList();
            return getRecentGpsRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * camera 최근 데이터 조회
     */
    public List<GetRecentCamera> getRecentCamera() throws BaseException {
        try {
            List<GetRecentCamera> getRecentCameraRes = adminDao.getCameraList();
            return getRecentCameraRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    /**
     * 데이터 사용량 확인 조회
     */
    public List<GetDataCheck> getDataCheck() throws BaseException {
        try {
            List<GetDataCheck> getDataCheckRes = adminDao.getDataCheck();
            return getDataCheckRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}