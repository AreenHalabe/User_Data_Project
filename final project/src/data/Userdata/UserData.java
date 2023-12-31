package data.Userdata;

import data.Api.Controller;
import data.Api.ControllerFactory;
import data.Application;
import data.FileStorag.StoregeService;
import data.FileStorag.TextFile;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import exceptions.Util;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private StoregeService storegeService;
    private static List<Controller> Controllers;
    public UserData(){
        Controllers = new ArrayList<>();
    }


    public boolean validateName(String name){
        try {
            Util.validateUserName(name);
        }
        catch (SystemBusyException e){
            System.err.println(e.getMessage());
            return false;
        }
        catch (BadRequestException e){
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }



    public void ExportData()  {
        String name = Application.getLoginUserName();
        if(validateName(name)){
            try{
                var user = Application.getUserService().getUser(name);
                storegeService= new TextFile(name);
                Controllers = ControllerFactory.CreateController(user.getUserType());
                FetchData(Controllers , name , storegeService);
                Controllers.clear();
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


    private void FetchData(List<Controller> Controllers , String name , StoregeService storegeService)  {
        for (Controller controller : Controllers) {
            controller.getData(name, storegeService);
        }
    }


}
