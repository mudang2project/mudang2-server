package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.gps.model.GetTestLocation;
import com.demo.mudang2.src.gps.model.GetTestLocationRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;
import static com.demo.mudang2.config.BaseResponseStatus.FAILED_INSERT_GPS;

@Service
public class GpsService {
    private final GpsDao gpsDao;
    private final GpsProvider gpsProvider;

    @Autowired
    public GpsService(GpsDao gpsDao, GpsProvider gpsProvider) {
        this.gpsDao = gpsDao;
        this.gpsProvider = gpsProvider;
    }

    public GetTestLocationRes createTest(GetTestLocation getTestLocation) throws BaseException {
        try{
            int idx = gpsDao.createTest(getTestLocation);
            return new GetTestLocationRes(idx);

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


}