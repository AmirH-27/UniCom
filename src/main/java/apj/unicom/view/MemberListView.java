package apj.unicom.view;

import apj.unicom.data.FormPosition;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MemberListView extends JFrame {
    Channel channel;
    JScrollPane memberScrollPane;
    Container container;
    JList<String> displayList;
    ArrayList<String> memberList = new ArrayList<>();

    JButton backButton;
    private void initializeView() {
        setTitle("UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        container = getContentPane();
        container.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(FormPosition.FORM_POSITION.width - 150, FormPosition.FORM_POSITION.height - 100, 100, 20);
        for(User user: channel.getMembers()) {
            memberList.add(user.getUserName());
        }
        displayList = new JList<>(memberList.toArray(new String[0]));
        memberScrollPane = new JScrollPane(displayList);
        memberScrollPane.setBounds(50, 50, FormPosition.FORM_POSITION.width - 150, FormPosition.FORM_POSITION.height - 200);
        container.add(memberScrollPane);
        container.add(backButton);
    }
    public MemberListView(Channel channel){
        this.channel = channel;
        initializeView();
        //double click to view member
        displayList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int index = displayList.locationToIndex(e.getPoint());
                    User user = channel.getMembers().get(index);
                    new  MemberProfile(user);
                }
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }
}
