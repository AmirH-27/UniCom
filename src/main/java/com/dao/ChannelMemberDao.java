package com.dao;

import com.model.ChannelMember;

public interface ChannelMemberDao {
    public void addChannelMember(int channelId, int userId);
    public void removeChannelMember(int channelId, int userId);
    public ChannelMember get(int channelMemberId);
}
