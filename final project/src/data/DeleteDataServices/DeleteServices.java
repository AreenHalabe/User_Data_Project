package data.DeleteDataServices;

import data.Api.DeleteDataController.ContrrolerDeleteFactory;
import data.Api.DeleteDataController.IDeleteDataController;
import data.Application;
import data.Loggers.Logger;
import data.Loggers.Loggers;
import data.Userdata.UserData;
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
        var user= UserData.getUser();
        logger.NotifyAction(typedelete+" delete for his information");
        controllers= ContrrolerDeleteFactory.CreateController(typedelete,user.getUserType());
        System.out.println("Deleting data.......");
        DeleteData(controllers , name);
        Util.setSkipValidation(false);
    }
    /**
     * Clears consumer records the use of drivers.
     *
     * @param controllers cast off controllers
     * @param name  the username
     */
    public void DeleteData(List<IDeleteDataController> controllers , String name)  {
        for (IDeleteDataController controller:controllers) {
            controller.DeleteDataFromServices(name);
        }
    }
}
