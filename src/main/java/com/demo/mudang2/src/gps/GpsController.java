package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponse;
import com.demo.mudang2.src.gps.model.GetLocation;
import com.demo.mudang2.src.gps.model.GetTestLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.GET_LOCATION_FAILED;

@RestController
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private final GpsProvider gpsProvider;

    public GpsController(GpsProvider gpsProvider) { this.gpsProvider = gpsProvider; }

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
    @GetMapping("/gps/test/location")
    public String getTestLocation() {
        StringBuffer result  = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://210.102.178.97/gps/test");
            urlBuilder.append("&type=json");
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = null;
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            rd.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }


}
