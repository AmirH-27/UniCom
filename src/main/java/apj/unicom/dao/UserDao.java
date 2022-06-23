package apj.unicom.dao;

import apj.unicom.domain.User;

public interface UserDao {
    User getUser(String studentId);
}
