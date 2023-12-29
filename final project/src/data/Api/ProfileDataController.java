package data.Api;

import data.Application;
import data.FileStorag.StoregeService;
import data.Userdata.UserData;
import iam.IUserService;

public class ProfileDataController implements Controller{




    @Override
    public void getData(String name, StoregeService storegeService) {

        var user = Application.getUserService().getUser(name);

        String data ="name: "+ user.getUserName() + " , "+"email: "+user.getEmail() + " , "+ "phone: "+user.getPhoneNumber()+" , "+"type: "+user.getUserType()+
                    " , "+"Country: "+user.getCountry() + " , "+"city: "+user.getCity() + " , "+ "department: "+user.getDepartment();

        storegeService.uploadData(data);


    }
}
