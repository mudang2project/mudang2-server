package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.demo.mudang2.config.BaseResponseStatus.DATABASE_ERROR;
import static com.demo.mudang2.config.BaseResponseStatus.GET_LOCATION_DELAY;

@Service
public class GpsProvider {
    private final GpsDao gpsDao;

    @Autowired
    public GpsProvider(GpsDao gpsDao) { this.gpsDao = gpsDao; }

    public List<GetLocation> getLocation() throws BaseException {
        try {
            int cnt = gpsDao.getCountGps();
            if(cnt == 0) {
                int newSql = gpsDao.insertLocation();
                List<GetLocation> getLocationRes = gpsDao.getLocation();
                return getLocationRes;
            }
            else {
                List<GetLocation> getLocationRes = gpsDao.getLocation();
                ////////////////////////////////


                PythonInterpreter interpreter = new PythonInterpreter();
                interpreter.execfile("scr/main/python/main.py");
                PyFunction pyFunction = interpreter.get("main",PyFunction.class);

                for (GetLocation getLocation : getLocationRes){
                    //int idx = getLocation.getBusIdx();
                    String lat = getLocation.getLat();
                    String lon = getLocation.getLon();

                    PyObject pyobj = pyFunction.__call__(new PyString(lat),new PyString(lon));
                    System.out.println(pyobj.toString());
                    List list = (List) pyobj.__tojava__(List.class);

                    getLocation.setLat((String) list.get(0));
                    getLocation.setLon((String) list.get(1));
                }
                 ///////////////////////////////


                return getLocationRes;
            }
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
