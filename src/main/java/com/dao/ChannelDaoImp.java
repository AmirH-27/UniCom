package com.dao;

import com.model.Channel;
import com.model.Course;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class ChannelDaoImp implements ChannelDao{

    private final SessionFactory sessionFactory;

    public ChannelDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Channel channel) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(channel);
    }

    @Override
    public Channel get(int channelId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Channel.class, channelId);
    }

    @Override
    public Map<String, Object> searchChannel(Course course, String channelSection) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Channel> channelQuery = session.createQuery("From Channel Where course_id = :course_id AND channel_section = :channel_section", Channel.class);
        channelQuery.setParameter("course_id", course.getCourseId());
        return null;
    }

    @Override
    public List<User> getChannelMembers(int channelId) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("From Channel where channelId = :channelId", User.class).list();
        return userList;
    }

    @Override
    public List<Channel> getAllChannels() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Channel> channelList = session.createQuery("From Channel", Channel.class).list();
        return channelList;
    }
}
