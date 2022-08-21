package com.service;

public interface UserService {
    boolean authenticateUser(String studentId, String userPass);
    boolean checkUser(String studentId);
}
