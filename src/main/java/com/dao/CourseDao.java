package com.dao;

import com.model.Course;

import java.util.ArrayList;
import java.util.List;

public interface CourseDao {
    Course getCourse(int courseId);
    List<Course> searchCourse(String searchKey);
}
