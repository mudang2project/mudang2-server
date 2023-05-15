package com.demo.mudang2.src.admin;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.admin.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private final AdminProvider adminProvider;
    private final AdminService adminService;

    /**
     * 로그인
     */
    @GetMapping("/login")
    public PasswordInfo loginAdmin() throws BaseException {
        PasswordInfo password = adminProvider.getPassword();
        return password;
    }

    /**
     * 비밀번호 변경
     */
    @PatchMapping("/password")
    public void changePassword(@RequestBody Map<String, Object> requestBody) throws BaseException {
        String password = (String) requestBody.get("password");
        String newPassword = adminService.changePassword(password);
    }

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