package data.Api;

import data.FileStorag.StoregeService;
import data.Userdata.UserData;
import iam.IUserService;

public class ProfileData implements Controller{

    private IUserService userService;



    public ProfileData(){
        this.userService= UserData.getUserService();
    }
    @Override
    public void getData(String name, StoregeService storegeService) {

        var user = UserData.getUserService().getUser(name);

        String data ="name: "+ user.getUserName() + " , "+"email: "+user.getEmail() + " , "+ "phone: "+user.getPhoneNumber()+" , "+"type"+user.getUserType()+
                    " , "+"Country: "+user.getCountry() + " , "+"city: "+user.getCity() + " , "+ "department: "+user.getDepartment();
        /*
        System.out.println("Name : "+user.getUserName());
        System.out.println("Email : "+user.getEmail());
        System.out.println("Phone : "+user.getPhoneNumber());
        System.out.println("User Type : "+user.getUserType());
        System.out.println("Country : "+user.getCountry());
        System.out.println("City : "+user.getCity());
        System.out.println("Department : "+user.getDepartment());*/
        storegeService.uploadData(data);


    }
}
