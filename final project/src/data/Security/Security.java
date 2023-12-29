package data.Security;

import data.Application;
import data.Exceptions.exception;
import data.Userdata.UserData;
import iam.IUserService;
import iam.UserType;

public class Security extends SecurityService{

    private static SecurityService securityService;


    private Security(){}




    public static SecurityService CreatSecurityService(){
        if(securityService==null){
            securityService= new Security();
        }
        return securityService;
    }



    @Override
    public boolean cheack(String name, String password) {
        var user = Application.getUserService().getUser(name);

        try{
            if (user == null) {
                throw new exception("User not found for name : " + name);
            }


           else if (name.equals(user.getUserName()) && password.equals(user.getPassword())) {
                setUserType(user.getUserType());
                return true;
            }


            else{
                throw new exception("invalid password");
            }

        }
        catch (exception message){
            System.err.println(message.getMessage());
        }

        return false;

    }
}
