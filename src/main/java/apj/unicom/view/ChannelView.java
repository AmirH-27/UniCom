package apj.unicom.view;

import apj.unicom.domain.Channel;
import apj.unicom.domain.User;

import javax.swing.*;

public class ChannelView extends JFrame {
    public ChannelView(Channel channel) {
        setTitle("Channel, " + channel.getCourse().getCourseName());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
