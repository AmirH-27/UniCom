package apj.unicom.implement.dao;

import apj.unicom.dao.CourseDao;
import apj.unicom.data.Response;
import apj.unicom.data.SqlQuery;
import apj.unicom.domain.Course;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
        String searchKey1 = "%" + searchKey + "%";
        List<Course> courses = jdbcTemplate.query(
                SqlQuery.SEARCH_COURSE.getQuery(),
                ((rs, rowNum) -> new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_code"))),
                searchKey1);
        return courses.isEmpty() ? new ArrayList<>() : new ArrayList<>(courses);
    }

    @Override
    public Response addCourseArchive(int userId, int courseId) {
        return jdbcTemplate.update(
                SqlQuery.ADD_COURSE_ARCHIVE.getQuery(),
                userId,
                courseId) == 1 ? Response.SUCCESS : Response.FAILURE;
    }
}
