package apj.unicom.implement.service;

import apj.unicom.domain.UserCredential;
import apj.unicom.service.UserCredentialService;

public class UserCredentialServiceImp implements UserCredentialService {
    private UserCredential userCredential;
    private int userNameLength = 2;
    private int userPassLength = 5;
    private String studentIdFormat = "[0-9]{2}-[0-9]{5}-[0-9]{1}";

    public UserCredentialServiceImp(UserCredential userCredential) {
        this.userCredential = userCredential;
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
                System.out.println("Has invalid char");
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

    public void setUserNameLength(int userNameLength) {
        this.userNameLength = userNameLength;
    }

    public void setUserPassLength(int userPassLength) {
        this.userPassLength = userPassLength;
    }

    public void setStudentIdFormat(String studentIdFormat) {
        this.studentIdFormat = studentIdFormat;
    }
}
