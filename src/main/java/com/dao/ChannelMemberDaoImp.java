package com.dao;

import com.model.ChannelMember;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelMemberDaoImp implements ChannelMemberDao {
    private final SessionFactory sessionFactory;

    public ChannelMemberDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addChannelMember(int channelId, int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        ChannelMember channelMember = new ChannelMember(channelId, userId);
        session.save(channelMember);
    }

    @Override
    public void removeChannelMember(int channelId, int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        ChannelMember channelMember = new ChannelMember(channelId, userId);
        session.delete(channelMember);
    }

    @Override
    public ChannelMember get(int channelMemberId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(ChannelMember.class, channelMemberId);
    }
}
