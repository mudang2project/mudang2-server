package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class GpsProvider {
    private final GpsDao gpsDao;

    @Autowired
    public GpsProvider(GpsDao gpsDao) { this.gpsDao = gpsDao; }

    public GetLocation getLocation(int busIdx) throws BaseException {
        try {
            GetLocation getLocationRes = gpsDao.getLocation(busIdx);
            return getLocationRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
