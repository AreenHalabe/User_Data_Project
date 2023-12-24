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
    private  static IUserActivityService iUserActivityService;
    private static IUserService userService;
    private static IPayment iPayment;
    private static IPostService iPostService;
    private static SecurityService securityService ;

    private StoregeService storegeService;

    private static List<Controller> Controllers;
    public UserData(IUserActivityService iUserActivityService , IUserService iUserService , IPayment iPayment , IPostService iPostService){
        this.iUserActivityService = iUserActivityService;
        this.userService=iUserService;
        this.iPayment=iPayment;
        this.iPostService=iPostService;
        securityService = new Security();
        Controllers = new ArrayList<>();
    }

    public static IUserActivityService getiUserActivityService() {
        return iUserActivityService;
    }

    public static IPayment getiPayment() {
        return iPayment;
    }

    public static IPostService getiPostService() {
        return iPostService;
    }


    public static IUserService getUserService(){
        return userService;
    }


    public void getData(String name , String password)  {
       boolean UserExist= securityService.cheack(name , password);
       if(UserExist){
           storegeService= new TextFile(name+".txt");
           Controllers = ControllerFactory.CreateController(securityService.getUsertype());
           FetchData(Controllers , name , storegeService);
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
