package data.Userdata;

import data.Api.FeatchDataController.Controller;
import data.Application;
import data.DeleteServices.DeleteServices;
import data.DeleteServices.IDeleteService;
import data.ExportDataServices.ExportServices;
import data.ExportDataServices.IExportServices;
import data.FileStorag.StoregeService;
import data.Loggers.Logger;
import data.Loggers.Loggers;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private IDeleteService deleteService;

    private IExportServices exportServices;


    public void Deletedata(String typeDelete){
        deleteService = new DeleteServices();
        String name = Application.getLoginUserName();
        deleteService.Delete(name , typeDelete);
    }



    public void ExportData(String typeOfExport)  {
        exportServices = new ExportServices();
        String name = Application.getLoginUserName();
        exportServices.Export(name , typeOfExport);
    }





}
