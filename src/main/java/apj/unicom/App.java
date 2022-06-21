package apj.unicom;


import apj.unicom.dao.UserDao;
import apj.unicom.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        UserDao userDao = applicationContext.getBean("userDao",UserDao.class);
        User user = new User(
                0,
                "19-4146-3",
                "Talha Jubayer",
                "19-41468-3@student.aiub.edu",
                "asdf1234"
        );

        //System.out.println(userDao.registerUser(user));
        System.out.println(userDao.checkUserEmail(user.getUserEmail()));

    }
}
