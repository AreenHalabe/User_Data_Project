package data.Api;

import data.Application;
import data.FileStorag.StoregeService;
import data.Userdata.UserData;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import iam.IUserService;

public class ProfileDataController implements Controller{




    @Override
    public void getData(String name, StoregeService storegeService)  {

        try{
            var user = Application.getUserService().getUser(name);
            String data ="name: "+ user.getUserName() + " , "+"email: "+user.getEmail() + " , "+ "phone: "+user.getPhoneNumber()+" , "+"type: "+user.getUserType()+
                         " , "+"Country: "+user.getCountry() + " , "+"city: "+user.getCity() + " , "+ "department: "+user.getDepartment();
            storegeService.uploadData(data);
            System.out.println("upload (Profile_Data) successfully.");
        }

         catch (BadRequestException e){
            System.err.println(e.getMessage());
        }
        catch (NotFoundException e){
            System.err.println(e.getMessage());
        }
        catch (SystemBusyException e){
            System.err.println(e.getMessage());
        }


    }
}
