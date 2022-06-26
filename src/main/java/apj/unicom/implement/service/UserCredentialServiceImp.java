package apj.unicom.implement.service;

import apj.unicom.data.Response;
import apj.unicom.domain.UserCredential;
import apj.unicom.service.UserCredentialService;

public class UserCredentialServiceImp implements UserCredentialService {
    private UserCredential userCredential;
    final private int userNameLength;
    final private int userPassLength;
    final private String studentIdFormat;

    public UserCredentialServiceImp(UserCredential userCredential, int userNameLength, int userPassLength, String studentIdFormat) {
        this.userCredential = userCredential;
        this.userNameLength = userNameLength;
        this.userPassLength = userPassLength;
        this.studentIdFormat = studentIdFormat;
    }

    @Override
    public Response isValidUserName() {
        if(userCredential.getUserName().isEmpty()){
            return Response.EMPTY_USER_NAME;
        }

        String[] userNameArray = userCredential.getUserName().split(" ");
        for(String userName : userNameArray) {
            if(userName.length() < userNameLength) {
                return Response.INVALID_USER_NAME_LENGTH;
            }
        }
        for(int i = 0; i < userCredential.getUserName().length(); i++) {
            if(!(Character.isLetter(userCredential.getUserName().charAt(i))
                    || userCredential.getUserName().charAt(i) == '.'
                    || userCredential.getUserName().charAt(i) == ','
                    || userCredential.getUserName().charAt(i) == ' ')) {
                return Response.INVALID_USER_NAME_CHARACTER;
            }
        }
        for (String userName : userNameArray) {
            if (!Character.isUpperCase(userName.charAt(0))) {
                return Response.INVALID_USER_NAME_FIRST_CHARACTER;
            }
        }

        return Response.SUCCESS;
    }

    @Override
    public Response isValidUserStudentId() {
        if(userCredential.getStudentId().isEmpty()) {
            return Response.EMPTY_STUDENT_ID;
        }
        return userCredential.getStudentId().matches(studentIdFormat)
                ? Response.SUCCESS : Response.INVALID_STUDENT_ID_FORMAT;
    }

    @Override
    public Response isValidUserPass() {
        if(userCredential.getUserPass().isEmpty()) {
            return Response.EMPTY_USER_PASS;
        }
        if (userCredential.getUserPass().length() < userPassLength) {
            return Response.INVALID_USER_PASS_LENGTH;
        }
        for (int i = 0; i < userCredential.getUserPass().length(); i++) {
            if (userCredential.getUserPass().charAt(i) == ' ') {
                return Response.INVALID_USER_PASS_CHARACTER;
            }
        }

        return Response.SUCCESS;
    }

    @Override
    public Response isValidConfirmPass() {
        if (userCredential.getConfirmPass().isEmpty()) {
            return Response.EMPTY_CONFIRM_PASS;
        }
        return userCredential.getConfirmPass().equals(userCredential.getUserPass())
                ? Response.SUCCESS : Response.INVALID_CONFIRM_PASS;
    }

}
