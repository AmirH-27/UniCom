package apj.unicom.dao;

import apj.unicom.data.Response;
import apj.unicom.domain.UserCredential;

public interface UserCredentialDao {
    Response checkUserStudentId(String studentId);
    Response checkUserCredential(String studentId, String userPass);
    Response registerUser(UserCredential userCredential);
    Response updateUser(UserCredential userCredential);
    Response deleteUser(UserCredential userCredential);
}