package apj.unicom.view;

import apj.unicom.dao.ChannelDao;
import apj.unicom.dao.CourseDao;
import apj.unicom.dao.UserCredentialDao;
import apj.unicom.dao.UserDao;
import apj.unicom.data.FormPosition;
import apj.unicom.data.Response;
import apj.unicom.domain.Channel;
import apj.unicom.domain.Course;
import apj.unicom.domain.User;
import apj.unicom.domain.UserCredential;
import apj.unicom.implement.dao.ChannelDaoImp;
import apj.unicom.implement.dao.CourseDaoImp;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.implement.service.PositionBoundServiceImp;
import apj.unicom.service.InputValidityService;
import apj.unicom.service.PositionBoundService;
import apj.unicom.service.UserCredentialService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class AddChannelView extends JFrame {
    private HomeView homeView;

    private Container container;
    private JLabel lblSelectCourse, lblEnterSection, lblUpDivider, lblDownDivider, lblChannel;
    private JTextField txtEnterCourse, txtEnterSection;
    private JButton btnSearch, btnAdd;
    private JComboBox<String> cmbSelectCourse;

    private PositionBoundService<JLabel> labelPositionBoundService;
    private PositionBoundService<JTextField> textFieldPositionBoundService;
    private PositionBoundService<JButton> buttonPositionBoundService;
    private PositionBoundService<JComboBox> comboBoxPositionBoundService;

    private ApplicationContext applicationContext;
    private User user;
    Channel channel;
    private UserDao userDao;
    private ChannelDao channelDao;
    private CourseDao courseDao;
    private ArrayList<Course> courses;
    private ArrayList<String> courseNames;
    private Course selectedCourse;
    private String selectedCourseName;
    private InputValidityService validityService;
    private Response response;
    Response channelResponse;

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

        lblUpDivider = new JLabel("");
        lblDownDivider = new JLabel("");
        lblChannel = new JLabel("");

        lblDownDivider.setBackground(Color.BLACK);
        lblDownDivider.setBackground(Color.BLACK);

        cmbSelectCourse = new JComboBox<>();
        cmbSelectCourse.setEditable(true);
        cmbSelectCourse.setMaximumRowCount(8);

        txtEnterCourse = new JTextField();
        txtEnterSection = new JTextField();

        btnSearch = new JButton("Search");
        btnAdd = new JButton("");

        labelPositionBoundService = new PositionBoundServiceImp<>();
        textFieldPositionBoundService = new PositionBoundServiceImp<>();
        buttonPositionBoundService = new PositionBoundServiceImp<>();
        comboBoxPositionBoundService = new PositionBoundServiceImp<>();

        labelPositionBoundService.setPosition(lblSelectCourse, FormPosition.ADD_CHANNEL_SEARCH_LABEL);
        labelPositionBoundService.setPosition(lblEnterSection, FormPosition.ADD_CHANNEL_SECTION_LABEL);

        labelPositionBoundService.setPosition(lblUpDivider, FormPosition.ADD_CHANNEL_UP_DIVIDER);
        labelPositionBoundService.setPosition(lblDownDivider, FormPosition.ADD_CHANNEL_DOWN_DIVIDER);
        labelPositionBoundService.setPosition(lblChannel, FormPosition.ADD_CHANNEL_INFO_LABEL);

        //textFieldPositionBoundService.setPosition(txtEnterCourse, FormPosition.ADD_CHANNEL_SEARCH_TEXT_FIELD);
        textFieldPositionBoundService.setPosition(txtEnterSection, FormPosition.ADD_CHANNEL_SECTION_TEXT_FIELD);

        comboBoxPositionBoundService.setPosition(cmbSelectCourse, FormPosition.ADD_CHANNEL_SEARCH_TEXT_FIELD);

        buttonPositionBoundService.setPosition(btnSearch, FormPosition.ADD_CHANNEL_SEARCH_BUTTON);
        buttonPositionBoundService.setPosition(btnAdd, FormPosition.ADD_CHANNEL_JOIN_BUTTON);

        container.add(lblSelectCourse);
        container.add(lblEnterSection);
        //container.add(txtEnterCourse);
        container.add(cmbSelectCourse);
        container.add(txtEnterSection);
        container.add(btnSearch);
    }

    private void initializeComponents(){
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        user = homeView.user;
        userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        channelDao = applicationContext.getBean("channelDao", ChannelDaoImp.class);
        courseDao = applicationContext.getBean("courseDao", CourseDaoImp.class);

        courses = new ArrayList<>();
    }

    public AddChannelView(HomeView homeView) {
        this.homeView = homeView;

        initializeComponents();
        initializeView();

        cmbSelectCourse.getEditor().getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String key = cmbSelectCourse.getEditor().getItem().toString().toUpperCase();
                updateCourses(key);
            }
        });

        btnSearch.addActionListener(e -> {
            container.remove(lblChannel);
            container.remove(lblDownDivider);
            container.remove(lblUpDivider);
            container.remove(btnAdd);
            repaint();
            revalidate();
            searchChannel();
        });

        btnAdd.addActionListener(e -> {
            addChannel();
        });
    }

    private void joinChannel(){
        Response joinResponse = userDao.joinChannel(user.getUserId(), channel.getChannelId());
        courseDao.addCourseArchive(user.getUserId(), channel.getCourseId());
        if(joinResponse == Response.SUCCESS){
            JOptionPane.showMessageDialog(null, "Successfully joined channel");
        }
        else{
            JOptionPane.showMessageDialog(null, "Failed to join channel");
        }
    }

    private void addChannel(){
        if(channelResponse == Response.CHANNEL_EXIST){
            joinChannel();
        }else{
            channelDao.addChannel(selectedCourse.getCourseId(), channel.getChannelSection());
            joinChannel();
        }
    }

    private void showChannel(){
        lblChannel.setText(channel.getCourse().getCourseName() + " [" + channel.getChannelSection() + "]\n" +
                "Members: " + channel.getMembers().size());
        container.add(lblChannel);
        container.add(lblUpDivider);
        container.add(lblDownDivider);

        if(channelResponse == Response.CHANNEL_EXIST){
            btnAdd.setText("Join");
            container.add(btnAdd);
        }else if(channelResponse == Response.CHANNEL_NOT_EXIST){
            btnAdd.setText("Create");
            container.add(btnAdd);
        }else if(channelResponse == Response.CHANNEL_ARCHIVED){
            JOptionPane.showMessageDialog(null, channelResponse.getMessage());
        }
        repaint();
        revalidate();
    }

    private void searchChannel(){
        getCourseByName();
        if(selectedCourse != null){
            String section = txtEnterSection.getText().toUpperCase();
            validityService = ()-> section.matches("[A-Z]{1,2}") ?
                        Response.SUCCESS : Response.INVALID_SECTION;
            response = validityService.isValid();
            if(response == Response.SUCCESS) {
                Map<String,Object> map = channelDao.searchChannel(selectedCourse, section, user.getUserId());
                channelResponse = (Response) map.get("response");
                channel = (Channel) map.get("channel");
                showChannel();
            }else{
                JOptionPane.showMessageDialog(null, response.getMessage());
            }

        }else{
            JOptionPane.showMessageDialog(null, Response.NO_COURSE_SELECTED.getMessage());
        }
    }

    private void updateCmbBox(){
        String text = cmbSelectCourse.getEditor().getItem().toString();
        cmbSelectCourse.removeAllItems();
        cmbSelectCourse.addItem(text);
        for(String courseName : courseNames){
            cmbSelectCourse.addItem(courseName);
        }
        cmbSelectCourse.showPopup();
    }

    private void updateCourseNames(){
        courseNames = new ArrayList<>();
        for(Course course : courses){
            courseNames.add(course.getCourseName());
        }
        updateCmbBox();
    }

    private void updateCourses(String key){
        courses = courseDao.searchCourse(key);
        updateCourseNames();
    }

    private void getCourseByName(){
        selectedCourse = null;
        selectedCourseName = cmbSelectCourse.getEditor().getItem().toString();
        for(Course course : courses){
            if(course.getCourseName().equals(selectedCourseName)){
                selectedCourse = course;
                break;
            }
        }
    }
}
