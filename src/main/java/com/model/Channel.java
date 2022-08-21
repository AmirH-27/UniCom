package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @Column(name="channel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelId;
    @Column(name="channel_section")
    private String channelSection;
    @Column(name="course_id")
    private int courseId;
    @Column(name="semester_id")
    private String semesterId;

    private Course course;
    private ArrayList<User> members;

    public Channel() {
    }

    public Channel(int channelId, String channelSection, int courseId, String semesterId) {
        this.channelId = channelId;
        this.channelSection = channelSection;
        this.courseId = courseId;
        this.semesterId = semesterId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelSection() {
        return channelSection;
    }

    public void setChannelSection(String channelSection) {
        this.channelSection = channelSection;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
}