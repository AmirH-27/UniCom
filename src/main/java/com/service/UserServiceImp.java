package com.service;

import com.dao.UserDao;
import com.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean authenticateUser(String studentID, String userPass) {
        return userDao.authenticateUser(studentID, userPass);
    }

    @Override
    public boolean checkUser(String studentID) {
        return userDao.checkUser(studentID);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
