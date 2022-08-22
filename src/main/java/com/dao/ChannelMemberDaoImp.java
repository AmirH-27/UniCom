package com.dao;

import com.model.MemberChannel;
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
        MemberChannel channelMember = new MemberChannel(channelId, userId);
        session.save(channelMember);
    }

    @Override
    public void removeChannelMember(int channelId, int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        MemberChannel channelMember = new MemberChannel(channelId, userId);
        session.delete(channelMember);
    }

    @Override
    public MemberChannel get(int channelMemberId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(MemberChannel.class, channelMemberId);
    }
}
