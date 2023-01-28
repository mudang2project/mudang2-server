package com.demo.mudang2.src.admin;

import com.demo.mudang2.src.admin.model.GetPower;
import com.demo.mudang2.src.admin.model.GetRecentData;
import com.demo.mudang2.src.admin.model.GetRecentGps;
import com.demo.mudang2.src.camera.model.GetHeadCount;
import com.demo.mudang2.src.gps.model.GetLocation;
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

    public GetRecentData getRecentData() {
        String getDataQuery = "select headCount, createdAt,\n" +
                "       case when timestampdiff(minute, createdAt, CURRENT_TIMESTAMP()) <= 0 then CONCAT(TIMESTAMPDIFF(second , createdAt , NOW()), '초 전')\n" +
                "            when timestampdiff(minute, createdAt, current_timestamp()) < 60 then CONCAT(TIMESTAMPDIFF(minute, createdAt , NOW()), '분 전')\n" +
                "            when timestampdiff(hour, createdAt, current_timestamp()) < 24 then CONCAT(TIMESTAMPDIFF(hour, createdAt , NOW()), '시간 전')\n" +
                "            when timestampdiff(day, createdAt, current_timestamp()) < 30 then CONCAT(TIMESTAMPDIFF(day, createdAt , NOW()), '일 전')\n" +
                "            else date_format(createdAt, '%Y년-%n월-%d일') end 'update'\n" +
                "from camera_device\n" +
                "order by createdAt DESC\n" +
                "limit 1";
        return this.jdbcTemplate.queryForObject(getDataQuery,
                (rs, rowNum) -> {
                    List<GetRecentGps> getGpsList = getGpsList();
                    GetRecentData data = new GetRecentData(
                            getGpsList,
                            rs.getInt("headCount"),
                            rs.getDate("createdAt"),
                            rs.getString("update"));
                    return data;
                });
    }

    public List<GetRecentGps> getGpsList() {
        String getGpsQuery = "select busIdx,  lat, lon, createdAt,\n" +
                "       case when timestampdiff(minute, t.createdAt, CURRENT_TIMESTAMP()) <= 0 then CONCAT(TIMESTAMPDIFF(second , createdAt , NOW()), '초 전')\n" +
                "       when timestampdiff(minute, t.createdAt, current_timestamp()) < 60 then CONCAT(TIMESTAMPDIFF(minute, createdAt , NOW()), '분 전')\n" +
                "       when timestampdiff(hour, t.createdAt, current_timestamp()) < 24 then CONCAT(TIMESTAMPDIFF(hour, createdAt , NOW()), '시간 전')\n" +
                "       when timestampdiff(day, t.createdAt, current_timestamp()) < 30 then CONCAT(TIMESTAMPDIFF(day, createdAt , NOW()), '일 전')\n" +
                "       else date_format(t.createdAt, '%Y년-%n월-%d일') end 'update'\n" +
                " from ( select busIdx, lat, lon, createdAt\n" +
                "        from gps_device  as g\n" +
                "        where (busIdx, createdAt) in\n" +
                "        (select busIdx, max(createdAt) as time\n" +
                "        from gps_device\n" +
                "        group by busIdx)\n" +
                "        order by createdAt desc) as t\n" +
                " group by t.busIdx";

        return this.jdbcTemplate.query(getGpsQuery,
                (rs, rowNum) -> new GetRecentGps(
                        rs.getInt("busIdx"),
                        rs.getString("lat"),
                        rs.getString("lon"),
                        rs.getDate("createdAt"),
                        rs.getString("update"))
        );
    }

}