package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_id")
    @NotNull(message = "Student ID is required")
    @Pattern(regexp = "[0-9]{2}-[0-9]{5}-[1-3]{1}", message = "Student ID must be in the format: xx-xxxxx-x")
    private String studentID;

    @Column(name = "user_name")
    @NotNull(message = "User name is required")
    private String userName;

    @Column(name = "isPublic")
    private boolean isPublic;

    @Column(name = "user_email")
    @NotNull(message = "User email is required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email must be valid")
    private String userEmail;

    @Column(name = "user_pass")
    @NotNull(message = "User password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", message = "Password must be at least 4 characters long and contain at least one upper case letter, one lower case letter, one number and one special character")
    private String userPass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentID1() {
        return studentID;
    }

    public String getStudentID2() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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
