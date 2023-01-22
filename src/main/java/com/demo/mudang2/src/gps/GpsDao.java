package com.demo.mudang2.src.gps;


import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GpsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<GetLocation> getLocation() {
        String getLocationQuery = "select t.busIdx, t.lat, t.lon, t.interval from ( select busIdx, lat, lon, timestampdiff(second, createdAt, NOW()) as 'interval'\n" +
                "from gps_device where (busIdx, createdAt) in " +
                "(select busIdx, max(createdAt) as time from gps_device group by busIdx)\n" +
                "order by createdAt desc) as t group by t.busIdx";

        return this.jdbcTemplate.query(getLocationQuery,
                (rs, rowNum) -> new GetLocation(
                        rs.getInt("busIdx"),
                        rs.getString("lat"),
                        rs.getString("lon"),
                        rs.getLong("interval"))
        );
    }

    public int createLocation(int busIdx, String lat, String lon) {
        String createLocationQuery = "insert into gps_device (busIdx, lat, lon) values (?,?,?)";
        Object[] createLocationParams = new Object[]{busIdx, lat, lon}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createLocationQuery, createLocationParams);

        String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }
}