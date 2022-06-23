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
        if (!userCredential.getStudentId().matches(studentIdFormat)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidUserPass() {
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
        return userCredential.getConfirmPass().equals(userCredential.getUserPass());
    }

}
