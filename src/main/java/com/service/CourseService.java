package com.service;

import com.model.Course;

import java.util.List;

public interface CourseService {
    Course getCourse(int courseId);
    List<Course> searchCourse(String searchKey);
}
