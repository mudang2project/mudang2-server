package com.demo.mudang2.src.camera;

import com.demo.mudang2.src.camera.model.GetHeadCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Repository
public class CameraDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public GetHeadCount getHeadCount() {
        String getHeadCountQuery = "SELECT headCount, timestampdiff(second, createdAt, NOW()) as 'interval'\n" +
                "FROM camera_device \n" +
                "ORDER BY createdAt DESC LIMIT 1 ";
        return this.jdbcTemplate.queryForObject(getHeadCountQuery,
                (rs, rowNum) -> new GetHeadCount(
                        rs.getInt("headCount"),
                        rs.getLong("interval")));
    }

    //response 유

//    public int createHeadCount(int headCount) {
//        String createHeadCountQuery = "insert into camera_device (headCount) values (?)";
//        Object[] createHeadCountParams = new Object[]{headCount}; // 동적 쿼리의 ?부분에 주입될 값
//        this.jdbcTemplate.update(createHeadCountQuery, createHeadCountParams);
//
//        String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
//        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
//    }

    //response 무

    public int createHeadCount(int headCount) {
        String createHeadCountQuery = "insert into camera_device (headCount) values (?)";
        Object[] createHeadCountParams = new Object[]{headCount}; // 동적 쿼리의 ?부분에 주입될 값
        return this.jdbcTemplate.update(createHeadCountQuery, createHeadCountParams);
    }

}
