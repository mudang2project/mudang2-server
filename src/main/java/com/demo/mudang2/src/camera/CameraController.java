package com.demo.mudang2.src.camera;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.camera.model.GetHeadCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cameras")
public class CameraController {
    @Autowired
    private final CameraProvider cameraProvider;



    public CameraController(CameraProvider cameraProvider) {
        this.cameraProvider = cameraProvider;
    }

    @ResponseBody
    @GetMapping("/headcount")
    public BaseResponse<GetHeadCount> getHeadCount() {
        try {
            GetHeadCount getHeadCountRes = cameraProvider.getHeadCount();
            return new BaseResponse<>(getHeadCountRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }

    }
}
