package apj.unicom;

import apj.unicom.dao.ChannelDao;
import apj.unicom.dao.UserDao;
import apj.unicom.domain.Channel;
import apj.unicom.domain.Course;
import apj.unicom.domain.User;
import apj.unicom.implement.dao.ChannelDaoImp;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.view.HomeView;
import apj.unicom.view.LoginView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
