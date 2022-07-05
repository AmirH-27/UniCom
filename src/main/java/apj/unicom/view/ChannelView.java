package apj.unicom.view;

import apj.unicom.data.FormPosition;
import apj.unicom.domain.Channel;
import apj.unicom.implement.service.PositionBoundServiceImp;
import apj.unicom.service.PositionBoundService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChannelView extends JFrame {
    Channel channel;
    private Container container;
    //message ui
    JLabel messageLabel, channelNameLabel, channelSectionLabel, memberCountLabel, lblTitle;
    JTextArea txtMessage;
    JScrollPane messageScrollPane;
    JButton sendButton, backButton, leaveButton;

    //member ui
    JLabel memberLabel;
    JList memberList;
    JScrollPane memberScrollPane;
    JButton viewMemberButton;

    ArrayList<JLabel> messageLabelList = new ArrayList<>();

    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;
    private PositionBoundService<JTextArea> textAreaPositionBoundService;




    private void initializeView() {
        setTitle("UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        container = getContentPane();
        container.setLayout(null);

        lblTitle = new JLabel("Channel: " + channel.getCourse().getCourseName() + " [" + channel.getChannelSection() + "]");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        memberLabel = new JLabel("Members", SwingConstants.RIGHT);
        viewMemberButton = new JButton("View Members");
        leaveButton = new JButton("Leave");
        txtMessage = new JTextArea();
        sendButton = new JButton("Send");
        backButton = new JButton("Back");


        labelPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();
        textAreaPositionBoundService = new PositionBoundServiceImp<>();


        labelPositionBoundService.setPosition(lblTitle, FormPosition.CHANNEL_TITLE_LABEL);
        labelPositionBoundService.setPosition(memberLabel, FormPosition.MEMBER_LABEL);
        buttonPositionBoundService.setPosition(viewMemberButton, FormPosition.VIEW_MEMBER_BUTTON);
        textAreaPositionBoundService.setPosition(txtMessage, FormPosition.MESSAGE_TEXT_AREA);
        buttonPositionBoundService.setPosition(sendButton, FormPosition.CHANNEL_SEND_BUTTON);
        buttonPositionBoundService.setPosition(backButton, FormPosition.CHANNEL_BACK_BUTTON);

        container.add(lblTitle);
        container.add(memberLabel);
        container.add(viewMemberButton);
        container.add(leaveButton);
        container.add(txtMessage);
        container.add(sendButton);
        container.add(backButton);
    }
    public ChannelView(Channel channel) {
        this.channel = channel;
        initializeView();
        viewMemberButton.addActionListener(e -> {
            new MemberListView(channel);
        });
        backButton.addActionListener(e -> {
            dispose();
        });
        sendButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Under Construction");
        });

    }
}
