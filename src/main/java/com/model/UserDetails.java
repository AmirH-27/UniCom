package com.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDetails {
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "student_id")
    private String studentID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "isPublic")
    private boolean isPublic;

    @Column(name = "user_email")
    private String userEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
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
}
