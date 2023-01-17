package com.demo.mudang2.src.gps;

import com.demo.mudang2.src.gps.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class GpsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public GetLocation getLocation(int busIdx) {
        String getLocationQuery = "SELECT busIdx, lat, lon, timestampdiff(second, createdAt, NOW()) as 'interval'\n" +
                "FROM gps_device\n" +
                "WHERE busIdx = ?\n" +
                "ORDER BY createdAt DESC LIMIT 1";
        int getBusIdxParams = busIdx;
        return this.jdbcTemplate.queryForObject(getLocationQuery,
                (rs, rowNum) -> new GetLocation(
                        rs.getInt("busIdx"),
                        rs.getString("lat"), rs.getString("lon"), rs.getLong("interval")), getBusIdxParams
                );

    }
}