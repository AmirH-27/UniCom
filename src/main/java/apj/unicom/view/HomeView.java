package apj.unicom.view;

import apj.unicom.domain.User;

import javax.swing.*;

public class HomeView extends JFrame {
    User user;

    private void initializeView() {
        setTitle("Home - " + user.getUserName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public HomeView(User user) {
        this.user = user;

        initializeView();
    }
}
