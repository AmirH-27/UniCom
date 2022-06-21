package apj.unicom.domain;

public class User {
    int userId;
    String studentId;
    String userName;
    String userEmail;
    String userPass;

    public User(int userId, String studentId, String userName, String userEmail, String userPass) {
        this.userId = userId;
        this.studentId = studentId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
