package com.demo.mudang2.src.gps;

import com.demo.mudang2.src.gps.model.Bus;
import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GpsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<Bus> getLocation() {
        String getLocationQuery = "select * from ( select busIdx, lat, lon, timestampdiff(second, createdAt, NOW())\n" +
                "as 'interval' from gps_device where (busIdx, createdAt) in (\n" +
                "select busIdx, max(createdAt) as time from gps_device group by busIdx)\n" +
                "order by createdAt desc)t group by t.busIdx";

        RowMapper<Bus> rowMapper = (rs, rowNum) -> new Bus(rs.getInt("busIdx"), rs.getString("lat"),
                rs.getString("lon"), rs.getLong("interval"));
        return jdbcTemplate.query(getLocationQuery, rowMapper);
    }
}