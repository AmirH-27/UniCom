package com.service;

import com.model.ChannelMember;

public interface ChannelMemberService {
    void addChannelMember(int channelId, int userId);
    void removeChannelMember(int channelId, int userId);
    ChannelMember get(int channelMemberId);
}
