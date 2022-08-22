package com.dao;

import com.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImp implements CourseDao {
    private final SessionFactory sessionFactory;

    public CourseDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Course getCourse(int courseId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Course.class, courseId);
    }

    @Override
    public List<Course> searchCourse(String searchKey) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Course> courseList = session.createQuery("From Course Where courseName LIKE :searchKey", Course.class).setParameter("searchKey", "%" + searchKey + "%").list();
        return courseList;
    }
}
