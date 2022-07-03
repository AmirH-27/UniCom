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

public class SettingView extends JFrame {
    //User user;
    private Container container;
    private JLabel lblStudentId, lblUserName, lblUserPass, lblConfirmPass, lblPrivacy, lblTitle;
    private JTextField txtStudentId, txtUserName;
    private JPasswordField txtPass, txtConfirmPass;
    private JButton btnUpdate, btnBack;
    private JRadioButton rbPublic, rbPrivate;

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

    public void initializeView(){
        setTitle("Profile, "+ user.getUserName());
        setSize(FormPosition.FORM_POSITION.width, FormPosition.FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        container = getContentPane();
        container.setLayout(null);

        lblTitle = new JLabel("Profile, "+ user.getUserName());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        lblStudentId = new JLabel("Student ID: ", SwingConstants.RIGHT);
        lblUserName = new JLabel("User Name: ", SwingConstants.RIGHT);
//        lblUserPass = new JLabel("Password: ", SwingConstants.RIGHT);
//        lblConfirmPass = new JLabel("Confirm Password: ", SwingConstants.RIGHT);
        lblPrivacy = new JLabel("Privacy: ", SwingConstants.RIGHT);

        txtStudentId = new JTextField(user.getStudentId());
        txtUserName = new JTextField(user.getUserName());
//        txtPass = new JPasswordField();
//        txtConfirmPass = new JPasswordField();

        txtStudentId.setEditable(false);

        btnUpdate = new JButton("Update");
        btnBack = new JButton("Back");
        rbPublic = new JRadioButton("Public");
        rbPrivate = new JRadioButton("Private");
        rbPublic.setSelected(user.isPublic());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbPublic);
        buttonGroup.add(rbPrivate);

        labelPositionBoundService = new PositionBoundServiceImp<>();
        textFieldPositionBoundService = new PositionBoundServiceImp<>();
//        passwordFieldPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();
        radioButtonPositionBoundService = new PositionBoundServiceImp<>();

        labelPositionBoundService.setPosition(lblTitle, FormPosition.TITLE_LABEL);
        labelPositionBoundService.setPosition(lblStudentId, FormPosition.STUDENT_ID_LABEL);
        labelPositionBoundService.setPosition(lblUserName, FormPosition.USER_NAME_LABEL);
//        labelPositionBoundService.setPosition(lblUserPass, FormPosition.REGISTER_PASSWORD_LABEL);
//        labelPositionBoundService.setPosition(lblConfirmPass, FormPosition.CONFIRM_PASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblPrivacy, FormPosition.PRIVACY_LABEL);

        textFieldPositionBoundService.setPosition(txtStudentId, FormPosition.STUDENT_ID_TEXT_FIELD);
        textFieldPositionBoundService.setPosition(txtUserName, FormPosition.USER_NAME_TEXT_FIELD);
//        passwordFieldPositionBoundService.setPosition(txtPass, FormPosition.REGISTER_PASSWORD_TEXT_FIELD);
//        passwordFieldPositionBoundService.setPosition(txtConfirmPass, FormPosition.CONFIRM_PASSWORD_TEXT_FIELD);

        buttonPositionBoundService.setPosition(btnUpdate, FormPosition.REGISTER_BUTTON);
        buttonPositionBoundService.setPosition(btnBack, FormPosition.BACK_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPublic, FormPosition.PUBLIC_RADIO_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPrivate, FormPosition.PRIVATE_RADIO_BUTTON);

        container.add(lblTitle);
        container.add(lblStudentId);
        container.add(txtStudentId);
        container.add(lblUserName);
        container.add(txtUserName);
        container.add(lblUserPass);
        container.add(txtPass);
        container.add(lblConfirmPass);
        container.add(txtConfirmPass);
        container.add(lblPrivacy);
        container.add(rbPublic);
        container.add(rbPrivate);
        container.add(btnUpdate);
        container.add(btnBack);
    }
    private void initializeComponents() {
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);
        userCredential = applicationContext.getBean("userCredential", UserCredential.class);
        userCredentialService = applicationContext.getBean("userCredentialService", UserCredentialServiceImp.class);
    }
    public SettingView(User user) {
        this.user = user;
        initializeComponents();
        initializeView();

        btnUpdate.addActionListener(e -> {
            userCredential.setUserEmail();
            userCredential.setStudentId(txtStudentId.getText());
            userCredential.setUserName(txtUserName.getText());
            userCredential.setPublic(rbPublic.isSelected());
            response = userCredentialDao.updateUser(userCredential);
            if(response == Response.SUCCESS){
                JOptionPane.showMessageDialog(null, "Update Successful");
                invalidate();
                validate();
                repaint();
            }else{
                JOptionPane.showMessageDialog(null, "Update Failed");
            }
        });

    }
}
