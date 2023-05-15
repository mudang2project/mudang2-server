package com.demo.mudang2.src.admin;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.admin.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.GET_DATA_CHECK_FAILED;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
public class AdminController {
    private final AdminProvider adminProvider;
    private final AdminService adminService;


    /**
     * 디바이스 on/off 조회
     */
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

    /**
     * 버스powerStatus 확인 조회
     */
    @RequestMapping(value = "/power/status")
    public String getPowerStatus(Model model) throws BaseException {
        List<GetPower> getPowerRes = adminProvider.getPower();
        for(int i=0; i < getPowerRes.size(); i++) {
            Long interval = getPowerRes.get(i).getInterval();
            if(interval < 600) {
                getPowerRes.get(i).setStatus("on");
            }
        }
        model.addAttribute("getPowerResStatus", getPowerRes);
        return "main";
    }

    /**
     * gps 최근 데이터 조회
     */
    @ResponseBody
    @GetMapping("/recent/gps")
    public List<GetRecentGps> getRecentGps() throws BaseException {
        List<GetRecentGps> data = adminProvider.getRecentGps();
        return data;
    }

    /**
     * camera 최근 데이터 조회
     */
    @ResponseBody
    @GetMapping("/recent/camera")
    public List<GetRecentCamera> getRecentCamera() throws BaseException {
        List<GetRecentCamera> data = adminProvider.getRecentCamera();
        return data;
    }

    /**
     * 데이터 사용량 확인 조회
     */
    @RequestMapping(value = "/usage")
    public String getData(Model model) throws BaseException {
        model.addAttribute("dataList", adminProvider.getDataCheck());
        return "charts";
    }

    /**
     * 데이터 사용량 response 무
     */
    @ResponseBody
    @PostMapping("/data/{busIdx}/{data}")
    public void insertDataCheck(@PathVariable("busIdx") int busIdx, @PathVariable("data") Long data) throws BaseException {
        adminService.createDataCheck(busIdx, data);
    }


}