package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.mudang2.config.BaseResponseStatus.GET_LOCATION_FAILED;

@RestController
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private final GpsProvider gpsProvider;

    public GpsController(GpsProvider gpsProvider) { this.gpsProvider = gpsProvider; }

    @ResponseBody
    @GetMapping("/location/{busIdx}")
    public BaseResponse<GetLocation> location(@PathVariable("busIdx") int busIdx) {
        try {
            GetLocation getLocationRes = gpsProvider.getLocation(busIdx);

            if (getLocationRes == null) {
                return new BaseResponse<>(GET_LOCATION_FAILED);
            }

            return new BaseResponse<>(getLocationRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
