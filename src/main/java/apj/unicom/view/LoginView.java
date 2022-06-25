package apj.unicom.view;

import apj.unicom.dao.UserCredentialDao;
import apj.unicom.dao.UserDao;
import apj.unicom.data.LoginFormPosition;
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

public class LoginView extends JFrame{

    private enum ButtonState{
        NEXT,
        LOGIN,
        REGISTER
    }

    private Container container;
    private JLabel lblStudentId, lblUserName, lblUserPass, lblConfirmPass, lblPrivacy;
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

    private void initializeView(){
        setTitle("Welcome to UniCom");
        setSize(LoginFormPosition.FORM_POSITION.width, LoginFormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        container = getContentPane();
        container.setLayout(null);

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

        labelPositionBoundService.setPosition(lblStudentId, LoginFormPosition.STUDENT_ID_LABEL);
        labelPositionBoundService.setPosition(lblUserName, LoginFormPosition.USER_NAME_LABEL);
        labelPositionBoundService.setPosition(lblUserPass, LoginFormPosition.LOGIN_PASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblConfirmPass, LoginFormPosition.CONFIRM_PASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblPrivacy, LoginFormPosition.PRIVACY_LABEL);

        textFieldPositionBoundService.setPosition(txtStudentId, LoginFormPosition.STUDENT_ID_TEXT_FIELD);
        textFieldPositionBoundService.setPosition(txtUserName, LoginFormPosition.USER_NAME_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtPass, LoginFormPosition.LOGIN_PASSWORD_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtConfirmPass, LoginFormPosition.CONFIRM_PASSWORD_TEXT_FIELD);

        buttonPositionBoundService.setPosition(btnNext, LoginFormPosition.NEXT_BUTTON);
        buttonPositionBoundService.setPosition(btnBack, LoginFormPosition.BACK_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPublic, LoginFormPosition.PUBLIC_RADIO_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPrivate, LoginFormPosition.PRIVATE_RADIO_BUTTON);

        container.add(lblStudentId);
        container.add(txtStudentId);
        container.add(btnNext);
        container.add(btnBack);
    }

    private void initializeComponents(){
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);
        userCredential = applicationContext.getBean("userCredential", UserCredential.class);
        userCredentialService = applicationContext.getBean("userCredentialService", UserCredentialServiceImp.class);
    }

    public LoginView(){

        initializeComponents();
        initializeView();

        btnNext.addActionListener(e -> {
            switch (currentButtonState){
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

    private void checkStudentId(){
        userCredential.setStudentId(txtStudentId.getText());
        validityService = userCredentialService::isValidUserStudentId;
        if(validityService.isValid()){
            if(userCredentialDao.checkUserStudentId(txtStudentId.getText())){
                container.add(lblUserPass);
                container.add(txtPass);
                labelPositionBoundService.setPosition(lblUserPass, LoginFormPosition.LOGIN_PASSWORD_LABEL);
                passwordFieldPositionBoundService.setPosition(txtPass, LoginFormPosition.LOGIN_PASSWORD_TEXT_FIELD);
                buttonPositionBoundService.setPosition(btnNext, LoginFormPosition.LOGIN_BUTTON);
                btnNext.setText("Login");
                btnBack.setText("Back");
                currentButtonState = ButtonState.LOGIN;
            } else{
                container.add(lblUserName);
                container.add(txtUserName);
                container.add(lblUserPass);
                container.add(txtPass);
                container.add(lblConfirmPass);
                container.add(txtConfirmPass);
                container.add(lblPrivacy);
                container.add(rbPublic);
                container.add(rbPrivate);
                labelPositionBoundService.setPosition(lblUserPass, LoginFormPosition.REGISTER_PASSWORD_LABEL);
                passwordFieldPositionBoundService.setPosition(txtPass, LoginFormPosition.REGISTER_PASSWORD_TEXT_FIELD);
                buttonPositionBoundService.setPosition(btnNext, LoginFormPosition.REGISTER_BUTTON);
                btnNext.setText("Register");
                btnBack.setText("Back");
                currentButtonState = ButtonState.REGISTER;
            }
            repaint();
            revalidate();
        } else{
            JOptionPane.showMessageDialog(container, "Invalid Student ID");
        }
    }

    private void login(){
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        validityService = userCredentialService::isValidUserPass;
        if(validityService.isValid()){
            if(userCredentialDao.checkUserCredential(userCredential.getStudentId(), userCredential.getUserPass())){
                user = userDao.getUser(userCredential.getStudentId());
                dispose();
                new HomeView(user);
            } else{
                JOptionPane.showMessageDialog(container, "Wrong Password");
            }
        } else{
            JOptionPane.showMessageDialog(container, "Invalid Password");
        }
    }

    private void register(){
        userCredential.setUserEmail();
        userCredential.setUserName(txtUserName.getText());
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        userCredential.setConfirmPass(String.valueOf(txtConfirmPass.getPassword()));
        userCredential.setPublic(rbPublic.isSelected());
        validityService = () ->
                (userCredentialService.isValidUserName()
                        && userCredentialService.isValidUserPass()
                        && userCredentialService.isValidConfirmPass());
        if(validityService.isValid()){
            if(userCredentialDao.checkUserCredential(userCredential.getStudentId(), userCredential.getUserPass())){
                JOptionPane.showMessageDialog(container, "Student ID Already Exists");
            } else{
                userCredentialDao.registerUser(userCredential);
                user = userDao.getUser(userCredential.getStudentId());
                dispose();
                new HomeView(user);
            }
        } else{
            JOptionPane.showMessageDialog(container, "Invalid Information");
        }
    }

    private void back(){
        if(currentButtonState == ButtonState.LOGIN){
            container.remove(lblUserPass);
            container.remove(txtPass);
        } else if(currentButtonState== ButtonState.REGISTER){
            container.remove(lblUserName);
            container.remove(txtUserName);
            container.remove(lblUserPass);
            container.remove(txtPass);
            container.remove(lblConfirmPass);
            container.remove(txtConfirmPass);
            container.remove(lblPrivacy);
            container.remove(rbPublic);
            container.remove(rbPrivate);
        } else{
            System.exit(0);
        }
        btnBack.setText("Close");
        btnNext.setText("Next");
        buttonPositionBoundService.setPosition(btnNext, LoginFormPosition.NEXT_BUTTON);
        currentButtonState = ButtonState.NEXT;
        repaint();
        revalidate();
    }
}