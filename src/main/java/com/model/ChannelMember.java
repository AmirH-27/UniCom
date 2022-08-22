package com.model;

import javax.persistence.*;

@Entity
@Table(name = "channel_member")
public class ChannelMember {

    @Id
    @Column(name="channel_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelMemberId;

    @Column(name="channel_id")
    private int channelId;

    @Column(name="user_id")
    private int userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="channel_id", insertable = false, updatable = false)
    private Channel channel;

    public ChannelMember() {
    }

    public ChannelMember(int channelId, int userId) {
        this.channelId = channelId;
        this.userId = userId;
    }

    public int getChannelMemberId() {
        return channelMemberId;
    }

    public void setChannelMemberId(int channelMemberId) {
        this.channelMemberId = channelMemberId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
