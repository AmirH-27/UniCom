package apj.unicom.domain;

import apj.unicom.dao.ChannelDao;
import apj.unicom.dao.CourseDao;
import apj.unicom.implement.dao.ChannelDaoImp;
import apj.unicom.implement.dao.CourseDaoImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

public class Channel {
    private int channelId;
    private String channelSection;
    private int courseId;
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

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        ChannelDao channelDao = applicationContext.getBean("channelDao", ChannelDaoImp.class);
        CourseDao courseDao = applicationContext.getBean("courseDao", CourseDaoImp.class);

        this.members = channelDao.getChannelMembers(channelId);
        this.course = courseDao.getCourse(courseId);
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
