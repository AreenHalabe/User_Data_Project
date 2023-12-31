package data.Api;

import data.FileStorag.StoregeService;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

public interface Controller {
    public void getData(String name , StoregeService storegeService) ;
}
