package com.service;

import com.dao.ChannelDao;
import com.model.Channel;
import com.model.Course;
import com.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ChannelServiceImp implements ChannelService {

    private ChannelDao channelDao;

    public ChannelServiceImp(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
    @Override
    public void save(Channel channel) {
        this.channelDao.save(channel);
    }

    @Override
    public Channel get(int channelId) {
       return this.channelDao.get(channelId);
    }

    @Override
    public Map<String, Object> searchChannel(Course course, String channelSection) {
        return this.channelDao.searchChannel(course, channelSection);
    }

    @Override
    public List<User> getChannelMembers(int channelId) {
        return this.channelDao.getChannelMembers(channelId);
    }

    @Override
    public List<Channel> getAllChannels() {
        return this.channelDao.getAllChannels();
    }
}