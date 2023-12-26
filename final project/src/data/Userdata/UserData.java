package data.Userdata;

import activity.IUserActivityService;
import data.Api.Controller;
import data.Api.ControllerFactory;
import data.FileStorag.StoregeService;
import data.FileStorag.TextFile;
import data.Security.Security;
import data.Security.SecurityService;
import iam.IUserService;
import iam.UserProfile;
import payment.IPayment;
import posts.IPostService;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static SecurityService securityService ;

    private StoregeService storegeService;

    private static List<Controller> Controllers;
    public UserData(){

        securityService = new Security();
        Controllers = new ArrayList<>();
    }


    public void getData(String name , String password){
       boolean UserExist= securityService.cheack(name , password);
       if(UserExist){
           storegeService= new TextFile(name+".txt");
           Controllers = ControllerFactory.CreateController(securityService.getUsertype());
           FetchData(Controllers , name , storegeService);
           System.out.println("num of controller : "+Controllers.size());
           System.out.println(securityService.getUsertype());
           Controllers.clear();
       }
       else{
           System.out.println("invalid password for this user");
       }
    }


    private void FetchData(List<Controller> Controllers , String name , StoregeService storegeService){
        for (Controller controller : Controllers) {
            controller.getData(name, storegeService);
        }
    }


}
