package com.service;

import com.model.User;

public interface UserService {
    boolean authenticateUser(String studentId, String userPass);
    boolean checkUser(String studentId);
    void save(User user);
}
