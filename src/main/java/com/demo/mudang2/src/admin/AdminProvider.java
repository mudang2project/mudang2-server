package com.demo.mudang2.src.admin;


import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.admin.model.GetPower;
import com.demo.mudang2.src.admin.model.GetRecentData;
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

    public List<GetPower> getPower() throws BaseException {
        try {
            List<GetPower> getPowerRes = adminDao.getPower();
            return getPowerRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetRecentData getRecentData() throws BaseException {
        try {
            GetRecentData getRecentDataRes = adminDao.getRecentData();
            return getRecentDataRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}