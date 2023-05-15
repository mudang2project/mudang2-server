package com.demo.mudang2.src.camera;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.camera.model.GetHeadCount;
import com.demo.mudang2.src.camera.model.PostHeadCountRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cameras")
public class CameraController {
    private final CameraProvider cameraProvider;
    private final CameraService cameraService;
    

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

    //response 유

//    @ResponseBody
//    @PostMapping("/headcount/{headCount}")
//    public BaseResponse<PostHeadCountRes> insertHeadCount(@PathVariable("headCount") int headCount) {
//        try{
//            PostHeadCountRes headCountRes = cameraService.createHeadCount(headCount);
//            return new BaseResponse<>(headCountRes);
//
//        }catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

    //response 무
    @ResponseBody
    @PostMapping("/headcount/{headCount}")
    public void insertHeadCount(@PathVariable("headCount") int headCount) throws BaseException {
            cameraService.createHeadCount(headCount);
    }

}
