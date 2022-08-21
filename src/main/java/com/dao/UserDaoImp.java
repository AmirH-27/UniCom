package com.dao;

import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean authenticateUser(String student_id, String userPass) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<User> userQuery = session.createQuery("From User Where student_id = :student_id AND user_pass = :password", User.class);
        userQuery.setParameter("student_id", student_id);
        userQuery.setParameter("password", userPass);
        try {
            userQuery.getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean checkUser(String student_id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<User> userQuery = session.createQuery("From User Where student_id = :student_id", User.class);
        userQuery.setParameter("student_id", student_id);
        try {
            userQuery.getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
