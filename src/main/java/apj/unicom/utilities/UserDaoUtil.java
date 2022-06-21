package apj.unicom.utilities;

import apj.unicom.domain.User;

import java.util.List;

public interface UserDaoUtil {
    User getUser(int userId);
    boolean checkUserEmail(String userEmail);
    boolean checkUserCredential(String userEmail, String userPass);
    boolean registerUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
