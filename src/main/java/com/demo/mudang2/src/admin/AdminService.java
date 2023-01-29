package com.demo.mudang2.src.admin;

import com.demo.mudang2.config.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.*;

@Service
public class AdminService {
    private final AdminDao adminDao;
    private final AdminProvider adminProvider;

    @Autowired
    public AdminService(AdminDao adminDao, AdminProvider adminProvider) {
        this.adminDao = adminDao;
        this.adminProvider = adminProvider;
    }

    //데이터사용량 response 무
    public void createDataCheck(int busIdx, Long data) throws BaseException {
        try{
            int result = adminDao.createDataCheck(busIdx, data);
            if(result == 0) {
                throw new BaseException(INSERT_FAIL_DATA_CHECK);

            }

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}