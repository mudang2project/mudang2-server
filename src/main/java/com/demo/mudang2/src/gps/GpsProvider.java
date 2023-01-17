package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;
import static com.demo.mudang2.config.BaseResponseStatus.GET_LOCATION_DELAY;

@Service
public class GpsProvider {
    private final GpsDao gpsDao;

    @Autowired
    public GpsProvider(GpsDao gpsDao) { this.gpsDao = gpsDao; }

    public GetLocation getLocation(int busIdx) throws BaseException {
        Long interval = gpsDao.getLocation(busIdx).getInterval();
        if(interval > 60) {
            throw new BaseException(GET_LOCATION_DELAY);
        }
        try {
            GetLocation getLocationRes = gpsDao.getLocation(busIdx);
            return getLocationRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
