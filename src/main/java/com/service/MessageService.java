package com.service;

import com.model.Message;

import java.util.List;

public interface MessageService {
    void add(Message message);
    List<Message> getAll(int channelId);
}
