package com.dao;

public interface UserDao {
    boolean authenticateUser(String studentId, String userPass);
    boolean checkUser(String studentId);
}
