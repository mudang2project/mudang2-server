package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.gps.model.GetLocation;
import com.demo.mudang2.src.gps.model.PostLocationRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.GET_LOCATION_FAILED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gps")
public class GpsController {
    private final GpsProvider gpsProvider;
    private final GpsService gpsService;


    @ResponseBody
    @GetMapping("/location")
    public BaseResponse<List<GetLocation>> location() {
        try {
            List<GetLocation> getLocationRes = gpsProvider.getLocation();

            if (getLocationRes == null) {
                return new BaseResponse<>(GET_LOCATION_FAILED);
            }

            return new BaseResponse<>(getLocationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //위치 저장 response 유

//    @ResponseBody
//    @PostMapping("/location/{busIdx}/{lat}/{lon}")
//    public BaseResponse<PostLocationRes> insertLocation(@PathVariable("busIdx") int busIdx, @PathVariable("lat") String lat, @PathVariable("lon") String lon) {
//        try{
//            PostLocationRes locationRes = gpsService.createLocation(busIdx, lat, lon);
//            return new BaseResponse<>(locationRes);
//
//        }catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

    //위치 저장 response 무

    @ResponseBody
    @PostMapping("/location/{busIdx}/{lat}/{lon}")
    public void insertLocation(@PathVariable("busIdx") int busIdx, @PathVariable("lat") String lat, @PathVariable("lon") String lon) throws BaseException{
            gpsService.createLocation(busIdx, lat, lon);

    }




}
