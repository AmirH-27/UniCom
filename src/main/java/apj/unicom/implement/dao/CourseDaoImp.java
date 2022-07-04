package apj.unicom.implement.dao;

import apj.unicom.dao.CourseDao;
import apj.unicom.data.SqlQuery;
import apj.unicom.domain.Course;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;

public class CourseDaoImp implements CourseDao {
    JdbcTemplate jdbcTemplate;

    public CourseDaoImp(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Course getCourse(int courseId) {
        return jdbcTemplate.queryForObject(
                SqlQuery.GET_COURSE.getQuery(),
                (rs, rowNum) -> new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code")),
                courseId);
    }

    @Override
    public ArrayList<Course> searchCourse(String searchKey) {
        return null;
    }
}
