package com.dao;

import com.model.User;

public interface UserDao {
    boolean authenticateUser(String studentId, String userPass);
    boolean checkUser(String studentId);
    void save(User user);
    int findUserIDByStudentId(String studentId);
    User get(int id);
}
