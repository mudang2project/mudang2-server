package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.gps.model.GetLocation;
import com.demo.mudang2.src.gps.model.PostLocationRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.GET_LOCATION_FAILED;

@RestController
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private final GpsProvider gpsProvider;
    @Autowired
    private final GpsService gpsService;

    public GpsController(GpsProvider gpsProvider, GpsService gpsService) {
        this.gpsProvider = gpsProvider;
        this.gpsService = gpsService;

    }

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


    @ResponseBody
    @PostMapping("/location/{busIdx}/{lat}/{lon}")
    public BaseResponse<PostLocationRes> insertLocation(@PathVariable("busIdx") int busIdx, @PathVariable("lat") String lat, @PathVariable("lon") String lon) {
        try{
            PostLocationRes locationRes = gpsService.createLocation(busIdx, lat, lon);
            return new BaseResponse<>(locationRes);

        }catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }




}
