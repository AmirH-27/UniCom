package com.service;

import com.dao.MessageDao;
import com.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImp implements MessageService{
    MessageDao messageDao;

    public MessageServiceImp(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public void add(Message message) {
        messageDao.add(message);
    }

    @Override
    public List<Message> getAll(int channelId) {
        return messageDao.getAll(channelId);
    }
}
