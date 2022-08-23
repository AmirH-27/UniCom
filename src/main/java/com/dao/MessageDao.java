package com.dao;

import com.model.Message;

import java.util.List;

public interface MessageDao {
    void add(Message message);
    List<Message> getAll(int channelId);
}
