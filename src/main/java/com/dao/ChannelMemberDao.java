package com.dao;

import com.model.MemberChannel;

public interface ChannelMemberDao {
    public void addChannelMember(int channelId, int userId);
    public void removeChannelMember(int channelId, int userId);
    public MemberChannel get(int channelMemberId);
}
