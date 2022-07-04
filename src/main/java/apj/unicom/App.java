package apj.unicom;

import apj.unicom.dao.UserDao;
import apj.unicom.domain.Channel;
import apj.unicom.domain.User;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.view.LoginView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        UserDao userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        User user = userDao.getUser("19-41429-3");
        System.out.println(user);
        //print all user info
        System.out.println(user.getUserId());
        System.out.println(user.getStudentId());
        System.out.println(user.getUserName());
        System.out.println(user.isPublic());
        System.out.println(user.getUserEmail());
        for(Channel channel : user.getChannels()){
            System.out.println(channel.getCourse().getCourseName());
        }
        SwingUtilities.invokeLater(LoginView::new);
    }
}
