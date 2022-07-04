package apj.unicom.dao;

import apj.unicom.data.Response;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;

import java.util.ArrayList;

public interface UserDao {
    User getUser(String studentId);
    ArrayList<Channel> getUserChannels(int userId);
    Response leaveChannel(int userId, int channelId);
    Response joinChannel(int userId, int channelId);
}
