package com.demo.mudang2.src.admin;

import com.demo.mudang2.src.admin.model.GetPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetPower> getPower() {
        String getPowerQuery = "(select t.busIdx as 'idx', t.interval from ( select busIdx, lat, lon, timestampdiff(second, createdAt, NOW()) as 'interval'\n" +
                "from gps_device where (busIdx, createdAt) in (select busIdx, max(createdAt) as time from gps_device group by busIdx)\n" +
                "order by createdAt desc) as t group by t.busIdx)\n" +
                "UNION\n" +
                "(select idx, timestampdiff(second, createdAt, NOW()) as 'interval'\n" +
                " from camera_device\n" +
                " order by createdAt DESC\n" +
                " limit 1)";

        return this.jdbcTemplate.query(getPowerQuery,
                (rs, rowNum) -> new GetPower(
                        rs.getInt("idx"),
                        rs.getLong("interval"), "off")
        );
    }

}