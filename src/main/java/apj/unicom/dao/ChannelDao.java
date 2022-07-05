package apj.unicom.dao;

import apj.unicom.data.Response;
import apj.unicom.domain.Channel;
import apj.unicom.domain.Course;
import apj.unicom.domain.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface ChannelDao {
    int addChannel(int courseId, String channelSection);
    Channel getChannel(int channelId);
    Map<String, Object> searchChannel(Course course, String channelSection, int userId);
    ArrayList<User> getChannelMembers(int channelId);
}
