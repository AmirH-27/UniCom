package apj.unicom.service;

import apj.unicom.data.Response;

public interface UserCredentialService {
    Response isValidUserName();
    Response isValidUserStudentId();
    Response isValidUserPass();
    Response isValidConfirmPass();
}
