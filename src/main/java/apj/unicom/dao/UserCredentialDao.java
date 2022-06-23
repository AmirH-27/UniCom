package apj.unicom.dao;

import apj.unicom.domain.UserCredential;

public interface UserCredentialDao {
    boolean checkUserStudentId(String studentId);
    boolean checkUserCredential(String studentId, String userPass);
    boolean registerUser(UserCredential userCredential);
    boolean updateUser(UserCredential userCredential);
    boolean deleteUser(UserCredential userCredential);
}