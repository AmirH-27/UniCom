package apj.unicom.dao;
import apj.unicom.data.Response;
import apj.unicom.domain.Course;

import java.util.ArrayList;

public interface CourseDao {
    Course getCourse(int courseId);
    ArrayList<Course> searchCourse(String searchKey);
    Response addCourseArchive(int userId, int courseId);
}