package apj.unicom.view;

import apj.unicom.dao.UserCredentialDao;
import apj.unicom.dao.UserDao;
import apj.unicom.data.FormPosition;
import apj.unicom.data.Response;
import apj.unicom.domain.User;
import apj.unicom.domain.UserCredential;
import apj.unicom.implement.dao.UserCredentialDaoImp;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.implement.service.PositionBoundServiceImp;
import apj.unicom.implement.service.UserCredentialServiceImp;
import apj.unicom.service.InputValidityService;
import apj.unicom.service.PositionBoundService;
import apj.unicom.service.UserCredentialService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private enum ButtonState {
        NEXT,
        LOGIN,
        REGISTER
    }

    private Container container;
    private JLabel lblStudentId, lblUserName, lblUserPass, lblConfirmPass, lblPrivacy, lblTitle;
    private JTextField txtStudentId, txtUserName;
    private JPasswordField txtPass, txtConfirmPass;
    private JButton btnNext, btnBack;
    private JRadioButton rbPublic, rbPrivate;
    private ButtonState currentButtonState;

    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JTextField> textFieldPositionBoundService;
    private PositionBoundService<JPasswordField> passwordFieldPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;
    private PositionBoundService<JRadioButton> radioButtonPositionBoundService;

    private ApplicationContext applicationContext;
    private UserCredentialDao userCredentialDao;
    private UserCredential userCredential;
    private UserCredentialService userCredentialService;
    private UserDao userDao;
    private User user;
    private InputValidityService validityService;
    private Response response;

    private void initializeView() {
        setTitle("Welcome to UniCom");
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        container = getContentPane();
        container.setLayout(null);

        lblTitle = new JLabel("Welcome");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        lblStudentId = new JLabel("Student ID: ", SwingConstants.RIGHT);
        lblUserName = new JLabel("User Name: ", SwingConstants.RIGHT);
        lblUserPass = new JLabel("Password: ", SwingConstants.RIGHT);
        lblConfirmPass = new JLabel("Confirm Password: ", SwingConstants.RIGHT);
        lblPrivacy = new JLabel("Privacy: ", SwingConstants.RIGHT);

        txtStudentId = new JTextField();
        txtUserName = new JTextField();
        txtPass = new JPasswordField();
        txtConfirmPass = new JPasswordField();

        btnNext = new JButton("Next");
        btnBack = new JButton("Close");
        rbPublic = new JRadioButton("Public");
        rbPrivate = new JRadioButton("Private");
        rbPublic.setSelected(true);
        currentButtonState = ButtonState.NEXT;

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbPublic);
        buttonGroup.add(rbPrivate);

        labelPositionBoundService = new PositionBoundServiceImp<>();
        textFieldPositionBoundService = new PositionBoundServiceImp<>();
        passwordFieldPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();
        radioButtonPositionBoundService = new PositionBoundServiceImp<>();

        labelPositionBoundService.setPosition(lblTitle, FormPosition.TITLE_LABEL);
        labelPositionBoundService.setPosition(lblStudentId, FormPosition.STUDENT_ID_LABEL);
        labelPositionBoundService.setPosition(lblUserName, FormPosition.USER_NAME_LABEL);
        labelPositionBoundService.setPosition(lblUserPass, FormPosition.LOGIN_PASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblConfirmPass, FormPosition.CONFIRM_PASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblPrivacy, FormPosition.PRIVACY_LABEL);

        textFieldPositionBoundService.setPosition(txtStudentId, FormPosition.STUDENT_ID_TEXT_FIELD);
        textFieldPositionBoundService.setPosition(txtUserName, FormPosition.USER_NAME_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtPass, FormPosition.LOGIN_PASSWORD_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtConfirmPass, FormPosition.CONFIRM_PASSWORD_TEXT_FIELD);

        buttonPositionBoundService.setPosition(btnNext, FormPosition.NEXT_BUTTON);
        buttonPositionBoundService.setPosition(btnBack, FormPosition.BACK_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPublic, FormPosition.PUBLIC_RADIO_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPrivate, FormPosition.PRIVATE_RADIO_BUTTON);

        container.add(lblTitle);
        container.add(lblStudentId);
        container.add(txtStudentId);
        container.add(btnNext);
        container.add(btnBack);
    }

    private void initializeComponents() {
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);
        userCredential = applicationContext.getBean("userCredential", UserCredential.class);
        userCredentialService = applicationContext.getBean("userCredentialService", UserCredentialServiceImp.class);
    }

    public LoginView() {

        initializeComponents();
        initializeView();

        btnNext.addActionListener(e -> {
            switch (currentButtonState) {
                case NEXT:
                    checkStudentId();
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
            }
        });

        btnBack.addActionListener(e -> back());
    }

    private void checkStudentId() {
        userCredential.setStudentId(txtStudentId.getText());
        validityService = userCredentialService::isValidUserStudentId;
        response = validityService.isValid();
        if (response == Response.SUCCESS) {
            response = userCredentialDao.checkUserStudentId(txtStudentId.getText());
            if (response == Response.SUCCESS) {
                txtStudentId.setEditable(false);
                container.add(lblUserPass);
                container.add(txtPass);
                labelPositionBoundService.setPosition(lblUserPass, FormPosition.LOGIN_PASSWORD_LABEL);
                passwordFieldPositionBoundService.setPosition(txtPass, FormPosition.LOGIN_PASSWORD_TEXT_FIELD);
                buttonPositionBoundService.setPosition(btnNext, FormPosition.LOGIN_BUTTON);
                lblTitle.setText("Login");
                btnNext.setText("Login");
                btnBack.setText("Back");
                currentButtonState = ButtonState.LOGIN;
            } else {
                txtStudentId.setEditable(false);
                container.add(lblUserName);
                container.add(txtUserName);
                container.add(lblUserPass);
                container.add(txtPass);
                container.add(lblConfirmPass);
                container.add(txtConfirmPass);
                container.add(lblPrivacy);
                container.add(rbPublic);
                container.add(rbPrivate);
                labelPositionBoundService.setPosition(lblUserPass, FormPosition.REGISTER_PASSWORD_LABEL);
                passwordFieldPositionBoundService.setPosition(txtPass, FormPosition.REGISTER_PASSWORD_TEXT_FIELD);
                buttonPositionBoundService.setPosition(btnNext, FormPosition.REGISTER_BUTTON);
                lblTitle.setText("Register");
                btnNext.setText("Register");
                btnBack.setText("Back");
                currentButtonState = ButtonState.REGISTER;
            }
            repaint();
            revalidate();
        } else {
            JOptionPane.showMessageDialog(container, response.getMessage());
        }
    }

    private void login() {
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        validityService = userCredentialService::isValidUserPass;
        response = validityService.isValid();
        if (response == Response.SUCCESS) {
            response = userCredentialDao.checkUserCredential(userCredential.getStudentId(), userCredential.getUserPass());
            if (response == Response.SUCCESS) {
                user = userDao.getUser(userCredential.getStudentId());
                dispose();
                new HomeView(user);
            } else {
                JOptionPane.showMessageDialog(container, response.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(container, response.getMessage());
        }
    }

    private void register() {
        userCredential.setUserEmail();
        userCredential.setUserName(txtUserName.getText());
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        userCredential.setConfirmPass(String.valueOf(txtConfirmPass.getPassword()));
        userCredential.setPublic(rbPublic.isSelected());
        validityService = () ->
                (userCredentialService.isValidUserName() == Response.SUCCESS
                        && userCredentialService.isValidUserPass() == Response.SUCCESS
                        && userCredentialService.isValidConfirmPass() == Response.SUCCESS)
                        ? Response.SUCCESS : Response.FAILURE;
        response = validityService.isValid();
        if (response == Response.SUCCESS) {
            response = userCredentialDao.registerUser(userCredential);
            if (response == Response.SUCCESS) {
                user = userDao.getUser(userCredential.getStudentId());
                dispose();
                new HomeView(user);
            } else {
                JOptionPane.showMessageDialog(container, response.getMessage());
            }
        } else {
            String message = "";
            response = userCredentialService.isValidUserName();
            if (userCredentialService.isValidUserName() != Response.SUCCESS) {
                message += response.getMessage() + "\n";
            }
            response = userCredentialService.isValidUserPass();
            if (userCredentialService.isValidUserPass() != Response.SUCCESS) {
                message += response.getMessage() + "\n";
            }
            response = userCredentialService.isValidConfirmPass();
            if (userCredentialService.isValidConfirmPass() != Response.SUCCESS) {
                message += response.getMessage() + "\n";
            }
            JOptionPane.showMessageDialog(container, message);
        }
    }

    private void back() {
        if (currentButtonState == ButtonState.LOGIN) {
            txtStudentId.setEditable(true);
            container.remove(lblUserPass);
            container.remove(txtPass);
        } else if (currentButtonState == ButtonState.REGISTER) {
            txtStudentId.setEditable(true);
            container.remove(lblUserName);
            container.remove(txtUserName);
            container.remove(lblUserPass);
            container.remove(txtPass);
            container.remove(lblConfirmPass);
            container.remove(txtConfirmPass);
            container.remove(lblPrivacy);
            container.remove(rbPublic);
            container.remove(rbPrivate);
        } else {
            int option = JOptionPane.showConfirmDialog(
                    container,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        lblTitle.setText("Welcome");
        btnBack.setText("Close");
        btnNext.setText("Next");
        buttonPositionBoundService.setPosition(btnNext, FormPosition.NEXT_BUTTON);
        currentButtonState = ButtonState.NEXT;
        repaint();
        revalidate();
    }
}