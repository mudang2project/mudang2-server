package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.gps.model.PostLocationRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.*;

@Service
public class GpsService {
    private final GpsDao gpsDao;
    private final GpsProvider gpsProvider;

    @Autowired
    public GpsService(GpsDao gpsDao, GpsProvider gpsProvider) {
        this.gpsDao = gpsDao;
        this.gpsProvider = gpsProvider;
    }

    //response 유

//    public PostLocationRes createLocation(int busIdx, String lat, String lon) throws BaseException {
//        try{
//            int idx = gpsDao.createLocation(busIdx, lat, lon);
//            return new PostLocationRes(idx);
//
//        }catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }

    //response 무

    public void createLocation(int busIdx, String lat, String lon) throws BaseException {
        try{
            int result = gpsDao.createLocation(busIdx, lat, lon);
            if(result == 0) {
                throw new BaseException(INSERT_FAIL_LOCATION);

            }

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}