package data.UserService;

import data.Api.DeleteData.ContrrolerDeleteFactory;
import data.Api.DeleteData.Icontroller;
import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

import java.util.ArrayList;
import java.util.List;

public class DeleteData  {
    private List<Icontroller> controllers;
    public DeleteData(){
        controllers= new ArrayList<>();
    }
    public void Delete(String username,String typedelete)  {
        try {
            var user= Application.getUserService().getUser(username);
            controllers= ContrrolerDeleteFactory.CreateController(typedelete,user.getUserType());
            System.out.println(controllers.size());
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
    public void DeleteData(List<Icontroller> controllers , String name)  {
        for (Icontroller controller:controllers) {
            controller.Delete(name);
        }
    }
}
