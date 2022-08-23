package com.model;

import javax.persistence.*;

@Entity
@Table(name = "user_course_archive")
public class UserCourseArchive {
    @Id
    @Column(name="user_course_archive_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCourseArchiveId;

    @Column(name="user_id")
    private int userId;

    @Column(name="course_id")
    private int courseId;

    @Column(name="semester_id")
    private String semesterId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="semester_id", insertable = false, updatable = false)
    private Semester semester;

    public int getUserCourseArchiveId() {
        return userCourseArchiveId;
    }

    public void setUserCourseArchiveId(int userCourseArchiveId) {
        this.userCourseArchiveId = userCourseArchiveId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
