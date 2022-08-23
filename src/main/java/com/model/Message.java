package com.model;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private int messageId;

    @Column(name="channel_id")
    private int channelId;

    @Column(name="sender_id")
    private int senderId;

    @Column(name="receiver_id")
    private int receiverId;

    @Column(name="message")
    private String message;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sender_id", insertable = false, updatable = false)
    private UserDetails sender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="receiver_id", insertable = false, updatable = false)
    private UserDetails receiver;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDetails getSender() {
        return sender;
    }

    public void setSender(UserDetails sender) {
        this.sender = sender;
    }

    public UserDetails getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDetails receiver) {
        this.receiver = receiver;
    }
}
