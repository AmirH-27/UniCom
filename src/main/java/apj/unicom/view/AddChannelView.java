package apj.unicom.view;

import apj.unicom.dao.ChannelDao;
import apj.unicom.dao.CourseDao;
import apj.unicom.dao.UserCredentialDao;
import apj.unicom.dao.UserDao;
import apj.unicom.data.FormPosition;
import apj.unicom.data.Response;
import apj.unicom.domain.User;
import apj.unicom.domain.UserCredential;
import apj.unicom.implement.dao.ChannelDaoImp;
import apj.unicom.implement.dao.CourseDaoImp;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.implement.service.PositionBoundServiceImp;
import apj.unicom.service.InputValidityService;
import apj.unicom.service.PositionBoundService;
import apj.unicom.service.UserCredentialService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

public class AddChannelView extends JFrame {
    private HomeView homeView;

    private Container container;
    private JLabel lblSelectCourse, lblEnterSection;
    private JTextField txtEnterCourse, txtEnterSection;
    private JButton btnSearch, btnAdd;

    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JTextField> textFieldPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;

    private ApplicationContext applicationContext;
    private User user;
    private UserDao userDao;
    private ChannelDao channelDao;
    private CourseDao courseDao;
    private InputValidityService validityService;
    private Response response;

    private void initializeView(){
        setTitle("Add Channel");
        setSize(800, 600);
        setSize(FormPosition.ADD_CHANNEL_FORM_POSITION.width, FormPosition.ADD_CHANNEL_FORM_POSITION.height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        container = getContentPane();
        container.setLayout(null);

        lblSelectCourse = new JLabel("Select Course");
        lblEnterSection = new JLabel("Enter Section");

        txtEnterCourse = new JTextField();
        txtEnterSection = new JTextField();

        btnSearch = new JButton("Search");
        btnAdd = new JButton("Add");

        labelPositionBoundService = new PositionBoundServiceImp<>();
        textFieldPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();

        labelPositionBoundService.setPosition(lblSelectCourse, FormPosition.ADD_CHANNEL_SEARCH_LABEL);
        labelPositionBoundService.setPosition(lblEnterSection, FormPosition.ADD_CHANNEL_SECTION_LABEL);

        textFieldPositionBoundService.setPosition(txtEnterCourse, FormPosition.ADD_CHANNEL_SEARCH_TEXT_FIELD);
        textFieldPositionBoundService.setPosition(txtEnterSection, FormPosition.ADD_CHANNEL_SECTION_TEXT_FIELD);

        buttonPositionBoundService.setPosition(btnSearch, FormPosition.ADD_CHANNEL_SEARCH_BUTTON);
        //buttonPositionBoundService.setPosition(btnAdd, FormPosition.ADD_CHANNEL_BUTTON_ADD);

        container.add(lblSelectCourse);
        container.add(lblEnterSection);
        container.add(txtEnterCourse);
        container.add(txtEnterSection);
        container.add(btnSearch);
    }

    private void initializeComponents(){
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        user = homeView.user;
        userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        channelDao = applicationContext.getBean("channelDao", ChannelDaoImp.class);
        courseDao = applicationContext.getBean("courseDao", CourseDaoImp.class);
    }

    public AddChannelView(HomeView homeView) {
        this.homeView = homeView;

        initializeComponents();
        initializeView();
    }
}
