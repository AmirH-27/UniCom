package apj.unicom.dao;
import apj.unicom.domain.Course;

import java.util.ArrayList;

public interface CourseDao {
    public Course getCourse(int courseId);
    public ArrayList<Course> searchCourse(String searchKey);
}