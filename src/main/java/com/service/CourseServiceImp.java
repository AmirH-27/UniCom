package com.service;

import com.dao.CourseDao;
import com.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CourseServiceImp implements CourseService {
    private CourseDao courseDao;
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    @Override
    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    public List<Course> searchCourse(String searchKey) {
        return courseDao.searchCourse(searchKey);
    }
}
