package com.demo.mudang2.src.gps;

import com.demo.mudang2.config.BaseException;
import com.demo.mudang2.config.BaseResponseStatus;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


                for (GetLocation getLocation : getLocationRes){
                    //int idx = getLocation.getBusIdx();
                    String lat = getLocation.getLat();
                    String lon = getLocation.getLon();

                    if (!Objects.equals(lat, "null") && !Objects.equals(lon, "null")){
                        try{
                            ProcessBuilder pb = new ProcessBuilder("python3","/home/mudang2/testdir/python/main.py",lat,lon);
                            Process p = pb.start();
                            int exitval = p.waitFor();

                            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
                            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                            String errorString = "";

                            while ((errorString = error.readLine()) != null) {
                                System.out.println(errorString);
                            }

                            try{
                                String line = "";
                                System.out.println("보정작업 들어가기 전");
                                while ((line = br.readLine()) != null) {
                                    System.out.println(line);
                                    if (line.contains("lat")){
                                        getLocation.setLat(line.substring(4));
                                    } else if (line.contains("lon")){
                                        getLocation.setLon(line.substring(4));
                                    }
                                }
                                if(exitval != 0){
                                    System.out.println("비정상종료:"+errorString);
                                }
                            } finally {
                                br.close();
                            }
                        } catch (Exception ignored) {
                            System.out.println("에러 발생"+ignored);
                        }

                    }
                }






//                PyFunction pyFunction;
//                try (PythonInterpreter interpreter = new PythonInterpreter()) {
//                    interpreter.execfile("scr/main/python/main.py");
//                    pyFunction = interpreter.get("main", PyFunction.class);
//                }
//
//                for (GetLocation getLocation : getLocationRes){
//                    //int idx = getLocation.getBusIdx();
//                    String lat = getLocation.getLat();
//                    String lon = getLocation.getLon();
//
//                    if (!Objects.equals(lat, "null") && !Objects.equals(lon, "null")){
//                        try{
//                            PyObject pyobj = pyFunction.__call__(new PyString(lat),new PyString(lon));
//                            System.out.println(pyobj.toString());
//                            List list = (List) pyobj.__tojava__(List.class);
//
//                            getLocation.setLat((String) list.get(0));
//                            getLocation.setLon((String) list.get(1));
//                        } catch (Exception ignored) {
//                            System.out.println("에러 발생"+ignored);
//                        }
//
//                    }
//                }
                 ///////////////////////////////


                return getLocationRes;
            }
        } catch (Exception exception) {
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
