package data.DeleteDataServices;

import data.Api.DeleteDataController.ContrrolerDeleteFactory;
import data.Api.DeleteDataController.IDeleteDataController;
import data.Application;
import data.Loggers.Logger;
import data.Loggers.Loggers;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import exceptions.Util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DeleteServices implements IDeleteService {
    private List<IDeleteDataController> controllers;
    private Loggers logger;
    public DeleteServices(){
        controllers= new ArrayList<>();
        logger = Logger.CreatLogger();
    }

    @Override
    public void Delete(String name,String typedelete)  {
        if(Instant.now().getEpochSecond()%3==0){
            try {
                // Waiting for 1 seconds (1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            var user= Application.getUserService().getUser(name);
            if(user != null){
                Util.setSkipValidation(true);
            }
            logger.NotifyAction(typedelete+" delete for his information");
            controllers= ContrrolerDeleteFactory.CreateController(typedelete,user.getUserType());
            System.out.println("Deleting data.......");
            DeleteData(controllers , name);
            Util.setSkipValidation(false);
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
    public void DeleteData(List<IDeleteDataController> controllers , String name)  {
        for (IDeleteDataController controller:controllers) {
            controller.DeleteDataFromServices(name);
        }
    }
}
