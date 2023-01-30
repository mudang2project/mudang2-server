package com.demo.mudang2.src.admin;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.admin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.GET_DATA_CHECK_FAILED;
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

    //디바이스 onoff 조회
    @ResponseBody
    @GetMapping("/power")
    public List<GetPower> getPower() throws BaseException {

        List<GetPower> getPowerRes = adminProvider.getPower();

        for(int i=0; i < getPowerRes.size(); i++) {
            Long interval = getPowerRes.get(i).getInterval();
            if(interval < 600) {
                getPowerRes.get(i).setStatus("on");
            }
        }
            return getPowerRes;
    }

    //최근 데이터 조회
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

    //gps 최근 데이터 조회
    @ResponseBody
    @GetMapping("/recent/gps")
    public List<GetRecentGps> getRecentGps() throws BaseException {
//        try{
//            List<GetRecentGps> getRecentGpsRes = adminProvider.getRecentGps();
//
//            if (getRecentGpsRes == null) {
//                return new BaseResponse<>(GET_DATA_CHECK_FAILED);
//            }
//            return getRecentGpsRes;
//
//        }catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
        List<GetRecentGps> data = adminProvider.getRecentGps();
        return data;
    }

    @ResponseBody
    @GetMapping("/recent/camera")
    public List<GetRecentCamera> getRecentCamera() throws BaseException {
        List<GetRecentCamera> data = adminProvider.getRecentCamera();
        return data;
    }

    //데이터 사용량 확인 조회
    @ResponseBody
    @GetMapping("/data")
    public List<GetDataCheck> getDataCheck() throws BaseException {
        List<GetDataCheck> getDataCheckRes = adminProvider.getDataCheck();

        return getDataCheckRes;


    }

    //데이터 사용량 response 무
    @ResponseBody
    @PostMapping("/data/{busIdx}/{data}")
    public void insertDataCheck(@PathVariable("busIdx") int busIdx, @PathVariable("data") Long data) throws BaseException {
        adminService.createDataCheck(busIdx, data);

    }



}