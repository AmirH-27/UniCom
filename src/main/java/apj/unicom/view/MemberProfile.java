package apj.unicom.view;

import apj.unicom.data.FormPosition;
import apj.unicom.domain.User;

import javax.swing.*;
import java.awt.*;

public class MemberProfile extends JFrame {
    User user;
    JLabel userNameLabel;
    JLabel studentIdLabel;
    JLabel emailLabel;

    JTextField userNameTextField;
    JTextField studentIdTextField;
    JTextField emailTextField;

    JButton backButton;
    JRadioButton rbPublic, rbPrivate;
    Container container;
    public MemberProfile(User user) {
        this.user = user;
        initializeView();
    }

    private void initializeView() {
        setTitle("UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        Container container = getContentPane();
        container.setLayout(null);

        rbPublic = new JRadioButton("Public");
        rbPublic.setSelected(user.isPublic());

        rbPrivate = new JRadioButton("Private");
        rbPrivate.setSelected(!user.isPublic());

        backButton = new JButton("Back");
        backButton.setBounds(700, 540, 80, 20);

        if(user.isPublic()) {
            userNameLabel = new JLabel("User Name");
            userNameLabel.setBounds(50, 50, 100, 30);
            container.add(userNameLabel);

            userNameTextField = new JTextField();
            userNameTextField.setBounds(150, 50, 200, 30);
            userNameTextField.setText(user.getUserName());
            container.add(userNameTextField);

            studentIdLabel = new JLabel("Student ID");
            studentIdLabel.setBounds(50, 100, 100, 30);
            container.add(studentIdLabel);

            studentIdTextField = new JTextField();
            studentIdTextField.setBounds(150, 100, 200, 30);
            studentIdTextField.setText(user.getStudentId());
            container.add(studentIdTextField);

            emailLabel = new JLabel("Email");
            emailLabel.setBounds(50, 150, 100, 30);
            container.add(emailLabel);

            emailTextField = new JTextField();
            emailTextField.setBounds(150, 150, 200, 30);
            emailTextField.setText(user.getStudentId() + "@student.aiub.edu");
            container.add(emailTextField);
            container.add(backButton);
        } else if (!user.isPublic()) {
            JOptionPane.showMessageDialog(null, "This user is private");
            this.dispose();
        }
        backButton.addActionListener(e -> {
            this.dispose();
        });
    }
}
