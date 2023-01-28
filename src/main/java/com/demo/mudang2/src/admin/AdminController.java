package com.demo.mudang2.src.admin;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.admin.model.GetPower;
import com.demo.mudang2.src.admin.model.GetRecentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.GET_POWER_FAILED;


@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private final AdminProvider adminProvider;
    @Autowired
    private final AdminService adminService;

    public AdminController(AdminProvider adminProvider, AdminService adminService) {
        this.adminProvider = adminProvider;
        this.adminService = adminService;
    }

    @ResponseBody
    @GetMapping("/power")
    public BaseResponse<List<GetPower>> getPower() {
        try {
            List<GetPower> getPowerRes = adminProvider.getPower();

            if (getPowerRes == null) {
                return new BaseResponse<>(GET_POWER_FAILED);
            }
            for(int i=0; i < getPowerRes.size(); i++) {
                Long interval = getPowerRes.get(i).getInterval();
                if(interval < 600) {
                    getPowerRes.get(i).setStatus("on");
                }
            }
            return new BaseResponse<>(getPowerRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/recent")
    public BaseResponse<GetRecentData> getRecentData() {
        try{
            GetRecentData getRecentDataRes = adminProvider.getRecentData();
            return new BaseResponse<>(getRecentDataRes);

        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }


    }



}