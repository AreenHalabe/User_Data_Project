package data.UserService;

import data.Api.DeleteData.ContrrolerDeleteFactory;
import data.Api.DeleteData.Icontroller;
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
    public void Delete(String username,String typedelete) throws SystemBusyException, NotFoundException, BadRequestException {
        controllers= ContrrolerDeleteFactory.CreateController(typedelete);
    }
}
