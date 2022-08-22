package com.service;

import com.dao.ChannelMemberDao;
import com.model.MemberChannel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ChannelMemberServiceImp implements ChannelMemberService{
    private final ChannelMemberDao channelMemberDao;
    public ChannelMemberServiceImp(ChannelMemberDao channelMemberDao) {
        this.channelMemberDao = channelMemberDao;
    }
    @Override
    public void addChannelMember(int channelId, int userId) {
        channelMemberDao.addChannelMember(channelId, userId);
    }

    @Override
    public void removeChannelMember(int channelId, int userId) {
        channelMemberDao.removeChannelMember(channelId, userId);
    }

    @Override
    public MemberChannel get(int channelMemberId) {
        return channelMemberDao.get(channelMemberId);
    }

}
