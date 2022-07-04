package apj.unicom.view;

import apj.unicom.data.FormPosition;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;

import javax.swing.*;

public class ChannelView extends JFrame {
    Channel channel;

    //message ui
    JLabel messageLabel, channelNameLabel, channelSectionLabel, memberCountLabel;
    JTextArea messageTextArea;
    JScrollPane messageScrollPane;
    JButton sendButton, backButton, memberCount, leaveButton;


    //member ui
    JLabel memberLabel;
    JList memberList;
    JScrollPane memberScrollPane;
    JButton viewMemberButton;


    private void initializeView() {
        setTitle("Channel - " + channel.getCourse().getCourseName()+"["+channel.getChannelSection()+"]");
        setSize(800, 600);
        setTitle("UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public ChannelView(Channel channel) {
        this.channel = channel;
        initializeView();

    }
}
