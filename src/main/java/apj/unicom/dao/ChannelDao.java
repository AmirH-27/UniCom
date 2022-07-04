package apj.unicom.dao;

import apj.unicom.data.Response;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;

import java.util.ArrayList;

public interface ChannelDao {
    Response addChannel(int courseId, String channelSection);
    Channel getChannel(int channelId);
    Channel searchChannel(int courseId, String channelSection);
    ArrayList<User> getChannelMembers(int channelId);
}
