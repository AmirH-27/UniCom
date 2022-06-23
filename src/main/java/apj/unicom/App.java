package apj.unicom;

import apj.unicom.dao.UserCredentialDao;
import apj.unicom.dao.UserDao;
import apj.unicom.domain.User;
import apj.unicom.domain.UserCredential;
import apj.unicom.implement.dao.UserCredentialDaoImp;
import apj.unicom.implement.dao.UserDaoImp;
import apj.unicom.implement.service.UserCredentialServiceImp;
import apj.unicom.service.UserCredentialService;
import apj.unicom.service.UserCredentialValidityService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        String userStudentId = "19-12945-2";
        String userName = "John";
        boolean isPublic = true;
        String userPass = "12345";
        String confirmPass = "12345";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

        UserDao userDao = applicationContext.getBean("userDao", UserDaoImp.class);
        UserCredentialDao userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);
        UserCredential userCredential = applicationContext.getBean("userCredential", UserCredential.class);
        UserCredentialService userCredentialService = applicationContext.getBean("userCredentialService", UserCredentialServiceImp.class);
        UserCredentialValidityService validityService;

        User user;

        //check if student id is already in use
        userCredential.setStudentId(userStudentId);
        validityService = userCredentialService::isValidUserStudentId;
        if(validityService.isValid()){
            if(userCredentialDao.checkUserStudentId(userCredential.getStudentId())){

                //login user
                userCredential.setUserPass(userPass);
                validityService = userCredentialService::isValidUserPass;
                if(validityService.isValid()){
                    if(userCredentialDao.checkUserCredential(userCredential.getStudentId(),userCredential.getUserPass())){
                        user = userDao.getUser(userCredential.getStudentId());
                        System.out.println("Login Successful: " + user.getUserName());
                    }
                    else{
                        System.out.println("Login Failed");
                    }
                }else {
                    System.out.println("Login Invalid");
                }

            }else{

                //register user
                userCredential.setUserName(userName);
                userCredential.setUserEmail();
                userCredential.setPublic(isPublic);
                userCredential.setUserPass(userPass);
                userCredential.setConfirmPass(confirmPass);
                validityService = () ->
                        (userCredentialService.isValidUserName() && userCredentialService.isValidUserPass() && userCredentialService.isValidConfirmPass());
                if(validityService.isValid()){
                    if(userCredentialDao.registerUser(userCredential)){
                        user = userDao.getUser(userCredential.getStudentId());
                        System.out.println("Register Successful: " + user.getUserName());
                    }
                    else{
                        System.out.println("Register Failed");
                    }
                }else {
                    System.out.println("Register Invalid");
                }

            }
        }else{
            System.out.println("ID Invalid");
        }
    }
}
