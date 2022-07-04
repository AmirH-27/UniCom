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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SettingView extends JFrame {
    //User user;
    private Container container;
    private JLabel lblStudentId, lblUserName, lblUserPass, lblConfirmPass, lblOldPass, lblPrivacy, lblTitle;
    private JTextField txtStudentId, txtUserName;
    private JPasswordField txtPass, txtConfirmPass, txtOldPass;
    private JButton btnUpdate, btnBack, btnDelete;
    private JRadioButton rbPublic, rbPrivate;

    private JCheckBox checkUpdatePass;

    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JTextField> textFieldPositionBoundService;
    private PositionBoundService<JPasswordField> passwordFieldPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;
    private PositionBoundService<JRadioButton> radioButtonPositionBoundService;
    private PositionBoundService<JCheckBox> checkBoxPositionBoundService;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        container = getContentPane();
        container.setLayout(null);

        lblTitle = new JLabel("Profile, "+ user.getUserName());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        lblStudentId = new JLabel("Student ID: ", SwingConstants.RIGHT);
        lblUserName = new JLabel("User Name: ", SwingConstants.RIGHT);
        lblUserPass = new JLabel("Password: ", SwingConstants.RIGHT);
        lblConfirmPass = new JLabel("Confirm Password: ", SwingConstants.RIGHT);
        lblOldPass = new JLabel("Current Password: ", SwingConstants.RIGHT);
        lblPrivacy = new JLabel("Privacy: ", SwingConstants.RIGHT);

        txtStudentId = new JTextField(user.getStudentId());
        txtUserName = new JTextField(user.getUserName());
        txtPass = new JPasswordField();
        txtConfirmPass = new JPasswordField();
        txtOldPass = new JPasswordField();
        txtStudentId.setEditable(false);

        checkUpdatePass = new JCheckBox("Update Password", false);

        btnUpdate = new JButton("Update");
        btnBack = new JButton("Back");
        btnDelete = new JButton("Delete");
        rbPublic = new JRadioButton("Public");
        rbPrivate = new JRadioButton("Private");
        rbPublic.setSelected(user.isPublic());
        rbPrivate.setSelected(!user.isPublic());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbPublic);
        buttonGroup.add(rbPrivate);

        labelPositionBoundService = new PositionBoundServiceImp<>();
        textFieldPositionBoundService = new PositionBoundServiceImp<>();
        passwordFieldPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();
        radioButtonPositionBoundService = new PositionBoundServiceImp<>();
        checkBoxPositionBoundService = new PositionBoundServiceImp<>();

        labelPositionBoundService.setPosition(lblTitle, FormPosition.SETTING_TITLE_LABEL);
        labelPositionBoundService.setPosition(lblStudentId, FormPosition.SETTINGS_STUDENTID_LABEL);
        labelPositionBoundService.setPosition(lblUserName, FormPosition.SETTINGS_USERNAME_LABEL);
        labelPositionBoundService.setPosition(lblUserPass, FormPosition.SETTINGS_PASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblConfirmPass, FormPosition.SETTINGS_CONFIRMPASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblOldPass, FormPosition.SETTINGS_OLDPASSWORD_LABEL);
        labelPositionBoundService.setPosition(lblPrivacy, FormPosition.SETTINGS_PRIVACY_LABEL);

        textFieldPositionBoundService.setPosition(txtStudentId, FormPosition.SETTINGS_STUDENTID_TEXT_FIELD);
        textFieldPositionBoundService.setPosition(txtUserName, FormPosition.SETTINGS_USERNAME_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtPass, FormPosition.SETTINGS_PASSWORD_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtConfirmPass, FormPosition.SETTINGS_CONFIRMPASSWORD_TEXT_FIELD);
        passwordFieldPositionBoundService.setPosition(txtOldPass, FormPosition.SETTINGS_OLDPASSWORD_TEXT_FIELD);

        checkBoxPositionBoundService.setPosition(checkUpdatePass, FormPosition.CHECK_BOX);
        buttonPositionBoundService.setPosition(btnDelete, FormPosition.SETTINGS_UNCHECKED_DELETE_BUTTON);
        buttonPositionBoundService.setPosition(btnUpdate, FormPosition.SETTINGS_UNCHECKED_UPDATE_BUTTON);
        buttonPositionBoundService.setPosition(btnBack, FormPosition.BACK_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPublic, FormPosition.SETTINGS_PUBLIC_RADIO_BUTTON);
        radioButtonPositionBoundService.setPosition(rbPrivate, FormPosition.SETTINGS_PRIVATE_RADIO_BUTTON);

        container.add(lblTitle);
        container.add(lblStudentId);
        container.add(txtStudentId);
        container.add(lblUserName);
        container.add(txtUserName);
        container.add(lblUserPass);
        container.add(txtPass);
        container.add(checkUpdatePass);
        container.add(lblPrivacy);
        container.add(rbPublic);
        container.add(rbPrivate);
        container.add(btnUpdate);
        container.add(btnDelete);
        container.add(btnBack);
    }

    private void initializeComponents() {
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);
        userCredential = applicationContext.getBean("userCredential", UserCredential.class);
        userCredentialService = applicationContext.getBean("userCredentialService", UserCredentialServiceImp.class);
    }

    public void updateWithoutPass(HomeView h1){
        userCredential.setStudentId(txtStudentId.getText());
        userCredential.setUserName(txtUserName.getText());
        if (rbPublic.isSelected()) {
            userCredential.setPublic(rbPublic.isSelected());
        } else {
            userCredential.setPublic(rbPrivate.isSelected());
        }
        userCredential.setUserEmail();
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        validityService = () ->
                (userCredentialService.isValidUserName() == Response.SUCCESS
                        && userCredentialService.isValidUserPass() == Response.SUCCESS)
                        ? Response.SUCCESS : Response.FAILURE;
        response = validityService.isValid();
        if(response == Response.SUCCESS) {
            response = userCredentialDao.checkUserCredential(userCredential.getStudentId(), userCredential.getUserPass());
            if(response == Response.SUCCESS) {
                response = userCredentialDao.updateUser(userCredential);
                if (response == Response.SUCCESS) {
                    JOptionPane.showMessageDialog(null, "Update Successful");
                    h1.user.setUserName(userCredential.getUserName());
                    h1.user.setPublic(userCredential.isPublic());
                    invalidate();
                    validate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Update Failed");
                }
            }
            else{
                JOptionPane.showMessageDialog(container, response.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(container, response.getMessage());
        }
    }
    public void updateWithPass(HomeView h1){
        userCredential.setStudentId(txtStudentId.getText());
        userCredential.setUserName(txtUserName.getText());
        if (rbPublic.isSelected()) {
            userCredential.setPublic(rbPublic.isSelected());
        } else {
            userCredential.setPublic(rbPrivate.isSelected());
        }
        userCredential.setUserEmail();
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        userCredential.setConfirmPass(String.valueOf(txtConfirmPass.getPassword()));
        String currentPass = String.valueOf(txtOldPass.getPassword());
        validityService = () ->
                (userCredentialService.isValidUserName() == Response.SUCCESS
                        && userCredentialService.isValidUserPass() == Response.SUCCESS
                        && userCredentialService.isValidConfirmPass() == Response.SUCCESS)
                        ? Response.SUCCESS : Response.FAILURE;
        response = validityService.isValid();
        if(response == Response.SUCCESS){
            response = userCredentialDao.checkUserCredential(userCredential.getStudentId(), currentPass);
            if(response == Response.SUCCESS){
                response = userCredentialDao.updateUser(userCredential);
                if (response == Response.SUCCESS) {
                    JOptionPane.showMessageDialog(null, "Update Successful");
                    h1.user.setUserName(userCredential.getUserName());
                    h1.user.setPublic(userCredential.isPublic());
                    invalidate();
                    validate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Update Failed");
                }
            }
            else{
                JOptionPane.showMessageDialog(container, response.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(container, response.getMessage());
        }
    }
    public SettingView(User user, HomeView h1) {
        this.user = user;
        initializeComponents();
        initializeView();
        userCredential.setUserPass(String.valueOf(txtPass.getPassword()));
        validityService = userCredentialService::isValidUserPass;
        response = validityService.isValid();

            btnUpdate.addActionListener(e -> {
               if(checkUpdatePass.isSelected()){
                   updateWithPass(h1);
               }
               else{
                   updateWithoutPass(h1);
               }
            });

        btnDelete.addActionListener(e->{
            int option = JOptionPane.showConfirmDialog(
                    container,
                    "Are you sure you want to Delete this account?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                userCredential.setStudentId(txtStudentId.getText());
                response = userCredentialDao.deleteUser(userCredential);
                if(response == Response.SUCCESS){
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                    dispose();
                    h1.dispose();
                    new LoginView();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Failed");
                }
            }
        });
        btnBack.addActionListener(e->{
            dispose();
            h1.invalidate();
            h1.validate();
            h1.repaint();
        });

        checkUpdatePass.addItemListener(e -> {
            if(checkUpdatePass.isSelected()) {
                lblUserPass.setText("New Password");
                buttonPositionBoundService.setPosition(btnDelete, FormPosition.DELETE_BUTTON);
                buttonPositionBoundService.setPosition(btnUpdate, FormPosition.UPDATE_BUTTON);
                container.add(lblConfirmPass);
                container.add(txtConfirmPass);
                container.add(lblOldPass);
                container.add(txtOldPass);
                container.add(btnUpdate);
                container.add(btnDelete);
                repaint();
                revalidate();
            }
            else{
                lblUserPass.setText("Password");
                buttonPositionBoundService.setPosition(btnDelete, FormPosition.SETTINGS_UNCHECKED_DELETE_BUTTON);
                buttonPositionBoundService.setPosition(btnUpdate, FormPosition.SETTINGS_UNCHECKED_UPDATE_BUTTON);
                container.remove(lblConfirmPass);
                container.remove(txtConfirmPass);
                container.remove(lblOldPass);
                container.remove(txtOldPass);
                container.add(btnUpdate);
                container.add(btnDelete);
                repaint();
                revalidate();
            }
        });

    }
}
