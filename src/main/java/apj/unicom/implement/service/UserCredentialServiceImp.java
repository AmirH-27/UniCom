package apj.unicom.implement.service;

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
    public boolean isValidUserName() {
        if(userCredential.getUserName().isEmpty()){
            return false;
        }

        String[] userNameArray = userCredential.getUserName().split(" ");
        for(String userName : userNameArray) {
            if(userName.length() < userNameLength) {
                return false;
            }
        }
        for(int i = 0; i < userCredential.getUserName().length(); i++) {
            if(!(!Character.isLetter(userCredential.getUserName().charAt(i))
                    || userCredential.getUserName().charAt(i) != '.'
                    || userCredential.getUserName().charAt(i) != ' ')) {
                return false;
            }
        }
        for (String userName : userNameArray) {
            if (!Character.isUpperCase(userName.charAt(0))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isValidUserStudentId() {
        if(userCredential.getStudentId().isEmpty()) {
            return false;
        }
        return userCredential.getStudentId().matches(studentIdFormat);
    }

    @Override
    public boolean isValidUserPass() {
        if(userCredential.getUserPass().isEmpty()) {
            return false;
        }
        if (userCredential.getUserPass().length() < userPassLength) {
            return false;
        }
        for (int i = 0; i < userCredential.getUserPass().length(); i++) {
            if (userCredential.getUserPass().charAt(i) == ' ') {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isValidConfirmPass() {
        if (userCredential.getConfirmPass().isEmpty()) {
            return false;
        }
        return userCredential.getConfirmPass().equals(userCredential.getUserPass());
    }

}
