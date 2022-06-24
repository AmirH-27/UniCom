package apj.unicom.view;
import apj.unicom.dao.UserCredentialDao;
import apj.unicom.dao.UserDao;
import apj.unicom.domain.User;
import apj.unicom.domain.UserCredential;
import apj.unicom.implement.dao.UserCredentialDaoImp;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.implement.service.UserCredentialServiceImp;
import apj.unicom.service.InputValidityService;
import apj.unicom.service.UserCredentialService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame{
    Container c;
    JLabel lblStudentId, lblUserName, lblUserPass, lblConfirmPass;
    JTextField txtStudentId, txtUserName;
    JPasswordField pass, confirmPass;
    JButton btnNext, btnLogin, btnRegister;
    JRadioButton rbPublic, rbPrivate;


    public void btnFunc(int x, int y, int h, int w, JButton btn){
        btn.setBounds(x, y, h, w);
    }

    public LoginFrame(){
        setTitle("Login Frame");
        setSize(500,400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        UserDao userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        UserCredentialDao userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);
        UserCredential userCredential = applicationContext.getBean("userCredential", UserCredential.class);
        UserCredentialService userCredentialService = applicationContext.getBean("userCredentialService", UserCredentialServiceImp.class);
        final InputValidityService[] validityService = new InputValidityService[1];
        User user;

        c = getContentPane();
        c.setLayout(null);
        lblStudentId = new JLabel("Student ID");
        lblUserName = new JLabel("User Name");
        lblUserPass = new JLabel("Password");
        lblConfirmPass = new JLabel("Confirm Password");

        lblStudentId.setBounds(10, 50, 100, 20);
        lblUserName.setBounds(10, 100, 100, 20);
        lblUserPass.setBounds(10, 150, 100, 20);
        lblConfirmPass.setBounds(10, 200, 150, 20);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(120, 50, 120, 20);

        txtUserName = new JTextField();
        txtUserName.setBounds(120, 100, 120, 20);

        pass = new JPasswordField();
        pass.setBounds(120, 150, 120, 20);

        confirmPass = new JPasswordField();
        confirmPass.setBounds(150, 200, 120, 20);

        btnNext = new JButton("Next");
        btnFunc(100, 200, 70, 20, btnNext);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 150, 70, 20);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(100, 300, 70, 20);

        rbPublic = new JRadioButton("Public");
        rbPublic.setBounds(120, 250, 70, 20);
        rbPublic.setSelected(true);

        rbPrivate = new JRadioButton("Private");
        rbPrivate.setBounds(200, 250, 70, 20);

        ButtonGroup bg = new ButtonGroup();

        bg.add(rbPublic);
        bg.add(rbPrivate);

        c.add(lblStudentId);
        c.add(txtStudentId);
        c.add(btnNext);

        String id = txtStudentId.getText();
        btnNext.addActionListener(e -> {

            userCredential.setStudentId(txtStudentId.getText());
            validityService[0] = userCredentialService::isValidUserStudentId;
            if(txtStudentId.getText().isEmpty()){
                JOptionPane.showMessageDialog(c, "Please enter your email");
            }
            else{
                if(validityService[0].isValid()){
                    if(userCredentialDao.checkUserStudentId(txtStudentId.getText())){
                        //JOptionPane.showMessageDialog(c, "Email already exists");
                        c.add(lblUserPass);
                        c.add(pass);
                        c.remove(btnNext);
                        c.add(btnLogin);
                        setVisible(true);
                        repaint();
                        revalidate();
                    }
                    else{
                        c.add(lblConfirmPass);
                        c.add(lblUserName);
                        c.add(txtUserName);
                        c.add(confirmPass);
                        c.add(lblUserPass);
                        c.add(pass);
                        c.add(rbPublic);
                        c.add(rbPrivate);
                        c.remove(btnNext);
                        c.add(btnRegister);
                        setVisible(true);
                        repaint();
                        revalidate();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(c, "Invalid email");
                }
            }
        });
        //action listener for login button
        btnLogin.addActionListener(e -> {
            String pass = String.valueOf(this.pass.getPassword());
            userCredential.setUserPass(pass);
            validityService[0] = userCredentialService::isValidUserPass;
            if(validityService[0].isValid()){
                if(userCredentialDao.checkUserCredential(txtStudentId.getText(), pass)){
                    JOptionPane.showMessageDialog(c, "Login Successful");
                    dispose();
                    new Home();
                }
                else{
                    JOptionPane.showMessageDialog(c, "Login Failed");
                }
            }
            else{
                JOptionPane.showMessageDialog(c, "Invalid email or password");
            }
        });

        //registration button action listener
        btnRegister.addActionListener(e -> {
            String pass = String.valueOf(this.pass.getPassword());
            String confirmPass = String.valueOf(this.confirmPass.getPassword());
            String userName = txtUserName.getText();

            userCredential.setUserEmail();
            userCredential.setUserPass(pass);
            userCredential.setUserName(userName);
            userCredential.setConfirmPass(confirmPass);
            userCredential.setPublic(rbPublic.isSelected());
            validityService[0] = () ->
                        (userCredentialService.isValidUserName()
                                && userCredentialService.isValidUserPass()
                                && userCredentialService.isValidConfirmPass());
            if(validityService[0].isValid()){
                userCredentialDao.registerUser(userCredential);
                JOptionPane.showMessageDialog(c, "Registration Successful");
                dispose();
                new Home();

            }
            else{
                JOptionPane.showMessageDialog(c, "Invalid email or password");
            }
        });

       setVisible(true);


    }

}
