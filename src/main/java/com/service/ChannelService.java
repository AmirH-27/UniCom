package com.service;

import com.model.Channel;
import com.model.Course;
import com.model.User;

import java.util.List;
import java.util.Map;

public interface ChannelService {
    void save(Channel channel);
    Channel get(int channelId);
    Map<String, Object> searchChannel(Course course, String channelSection);
    List<User> getChannelMembers(int channelId);
    List<Channel> getAllChannels();
}
