package com.service;

import com.model.MemberChannel;

public interface ChannelMemberService {
    void addChannelMember(int channelId, int userId);
    void removeChannelMember(int channelId, int userId);
    MemberChannel get(int channelMemberId);
}
