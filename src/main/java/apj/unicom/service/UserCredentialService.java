package apj.unicom.service;

public interface UserCredentialService {
    boolean isValidUserName();
    boolean isValidUserStudentId();
    boolean isValidUserPass();
    boolean isValidConfirmPass();
}
