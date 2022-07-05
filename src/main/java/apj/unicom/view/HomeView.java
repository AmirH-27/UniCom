package apj.unicom.view;

import apj.unicom.data.FormPosition;
import apj.unicom.data.Response;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;

import apj.unicom.implement.service.PositionBoundServiceImp;

import apj.unicom.service.PositionBoundService;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HomeView extends JFrame {
    User user;
    final int n = 3; //number of courses in a row
    ArrayList<Channel> channelList;
    int size, row, col;
    private Container container;
    private JLabel lblTitle;
    private JButton btnSettings, btnLogout, btnAddCourse;
    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;

    Map<JButton, Channel> btnList = new HashMap<>();

    private JPanel gridLayoutPanel;
    public void initializeParameter() {
        channelList = user.getChannels();
        size = channelList.size();
        row = (int) Math.ceil(size / n);
        col = 3;
    }
    public void initializeView() {
        setTitle("Home - " + user.getUserName());
        setSize(800, 600);
        setTitle("UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int x = 50;
        int y = 150;
        for (Channel channel : channelList) {
            String btnName = "<html>"+channel.getCourse().getCourseName()+"<br>"+"["+channel.getChannelSection()+"]"+"</html>";
            int channelId = channel.getChannelId();
            JButton btn = new JButton(btnName);
//            map.put(channelId, btn);
            if(x>600){
                x = 50;
                y += 200;
            }
            btn.setBounds(x, y, 150, 150);
            btnList.put(btn, channel);
            x+=200;
        }
        if(x>600){
            x = 50;
            y += 200;
        }
        btnAddCourse = new JButton("Add Course");
        btnAddCourse.setBounds(x, y, 150, 150);
        x+=200;
        container = getContentPane();
        container.setLayout(null);

        lblTitle = new JLabel("Welcome, "+ user.getUserName());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        btnSettings = new JButton("Settings");
        btnLogout = new JButton("Logout");

        labelPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();

        labelPositionBoundService.setPosition(lblTitle, FormPosition.HOME_LABEL);
        buttonPositionBoundService.setPosition(btnSettings, FormPosition.SETTING_BUTTON);
        buttonPositionBoundService.setPosition(btnLogout, FormPosition.BACK_BUTTON);

        container.add(lblTitle);
        container.add(btnSettings);
        container.add(btnLogout);
        container.add(btnAddCourse);
        for (JButton btn : btnList.keySet()) {
            container.add(btn);
        }
    }

    public HomeView(User user) {
        this.user = user;
        initializeParameter();
        initializeView();

        btnSettings.addActionListener(e -> {
            new SettingView(user, this);
        });

        btnLogout.addActionListener(e -> {
            this.dispose();
            new LoginView();
        });
        for (JButton btn : btnList.keySet()) {
            btn.addActionListener(e -> {
                new ChannelView(btnList.get(btn));
            });
        }
        btnAddCourse.addActionListener(e -> {
            new AddChannelView(this);
        });




    }
}