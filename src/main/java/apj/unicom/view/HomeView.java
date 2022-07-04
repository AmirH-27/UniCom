package apj.unicom.view;

import apj.unicom.data.FormPosition;
import apj.unicom.domain.User;
import apj.unicom.implement.service.PositionBoundServiceImp;
import apj.unicom.service.PositionBoundService;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {
    User user;
    //To do: courses
    private Container container;
    private JLabel lblTitle;
    private JButton btnSettings, btnLogout;
    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;

    private void initializeView() {
        setTitle("Home - " + user.getUserName());
        setSize(800, 600);
        setTitle("UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
    }

    public HomeView(User user) {
        this.user = user;

        initializeView();

        btnSettings.addActionListener(e -> {
            new SettingView(user, this);
        });

        btnLogout.addActionListener(e -> {
            dispose();
        });

    }
}