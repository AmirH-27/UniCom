package apj.unicom;

import apj.unicom.dao.UserCredentialDao;
import apj.unicom.domain.UserCredential;
import apj.unicom.implement.dao.UserCredentialDaoImp;
import apj.unicom.implement.service.UserCredentialServiceImp;
import apj.unicom.service.UserCredentialService;
import apj.unicom.service.UserCredentialValidityService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        String userStudentId = "19-12345-2";
        String userName = "John";
        boolean isPublic = true;
        String userPass = "12347";
        String confirmPass = "12345";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        UserCredentialDao userCredentialDao = applicationContext.getBean("userCredentialDao", UserCredentialDaoImp.class);

        UserCredential userCredential = new UserCredential();
        UserCredentialService userCredentialService = new UserCredentialServiceImp(userCredential);
        UserCredentialValidityService validityService;

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
                        System.out.println("Login Successful");
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
                        System.out.println("Register Successful");
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
