package com.demo.mudang2.src.admin;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.admin.model.PasswordInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDao adminDao;

    /**
     * 비밀번호 변경 - user table에 update
     */
    public String changePassword(PasswordInfo password) throws BaseException {
        try {
            int newPassword = adminDao.updatePassword(password);
            if(newPassword == 0) {
                throw new BaseException(MODIFY_FAIL_PASSWORD);
            }

            String result = "비밀번호 변경에 성공하였습니다.";
            return result;

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



    /**
     * 데이터사용량 response 무
     */

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