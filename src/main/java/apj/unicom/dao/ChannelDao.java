package apj.unicom.dao;

import apj.unicom.data.Response;
import apj.unicom.domain.Channel;

public interface ChannelDao {
    public Response  addChannel(Channel channel);
    public Channel getChannel(int channelId);
    public Channel[] getAllChannels();
}
