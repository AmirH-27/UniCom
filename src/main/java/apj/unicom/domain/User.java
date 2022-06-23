package apj.unicom.domain;

public class User {
    private int userId;
    private String studentId;
    private String userName;
    private boolean isPublic;
    private String userEmail;
    private String emailDomain = "student.aiub.edu";

    public User(){ }

    public User(int userId, String studentId, String userName, boolean isPublic, String userEmail) {
        this.userId = userId;
        this.studentId = studentId;
        this.userName = userName;
        this.isPublic = isPublic;
        this.userEmail = userEmail;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail() {
        userEmail = studentId + "@" + emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}
