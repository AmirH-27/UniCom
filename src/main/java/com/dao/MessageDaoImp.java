package com.dao;

import com.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImp implements MessageDao {

    private final SessionFactory sessionFactory;

    public MessageDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Message message) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(message);
    }

    @Override
    public List<Message> getAll(int channelId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Message> messageQuery = session.createQuery("From Message Where channelId = :channelId", Message.class);
        messageQuery.setParameter("channelId", channelId);
        return messageQuery.getResultList();
    }
}
