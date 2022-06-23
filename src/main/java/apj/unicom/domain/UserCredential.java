package apj.unicom.domain;

public class UserCredential extends User {
    private String userPass;
    private String confirmPass;
    final private String emailDomain;

    public UserCredential(String emailDomain){
        this.emailDomain = emailDomain;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public void setUserEmail() {
        setUserEmail(getStudentId() + "@" + emailDomain);
    }

}
