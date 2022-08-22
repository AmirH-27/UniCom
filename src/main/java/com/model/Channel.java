package com.model;

import javax.persistence.*;
import java.util.List;

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
    int courseId;
    @Column(name="semester_id")
    String semesterId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="course_id", insertable = false, updatable = false)
    private Course course;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="semester_id", insertable = false, updatable = false)
    private Semester semester;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id")
    private List<ChannelMember> channelMembers;

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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<ChannelMember> getChannelMembers() {
        return channelMembers;
    }

    public void setChannelMembers(List<ChannelMember> channelMembers) {
        this.channelMembers = channelMembers;
    }
}