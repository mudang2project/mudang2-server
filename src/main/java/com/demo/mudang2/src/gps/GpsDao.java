package com.demo.mudang2.src.gps;

import com.demo.mudang2.src.camera.model.GetLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class GpsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public GetLocation getLocation() {
        String getLocationQuery = "SELECT lat, lon FROM gps_device WHERE busIdx = ? ORDER BY createdAt DESC LIMIT 1";
        return this.jdbcTemplate.queryForObject(getLocationQuery,
                (rs, rowNum) -> new GetLocation(
                        rs.getString("lat"), rs.getString("lon")
                )
                );
    }
}