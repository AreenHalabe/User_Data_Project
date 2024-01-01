package data.ExportDataServices;

import data.Api.FeatchDataController.Controller;
import data.Api.FeatchDataController.ControllerFactory;
import data.Application;
import data.Converter.ExportConverter;
import data.FileStorag.StoregeService;
import data.FileStorag.TextFileStorage;
import data.Loggers.Logger;
import data.Loggers.Loggers;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import exceptions.Util;

import java.util.ArrayList;
import java.util.List;

public class ExportServices implements IExportServices{
    private StoregeService storegeService;
    private List<Controller> Controllers;
    private Loggers logger;
    private ExportConverter exportConverter;

    public ExportServices(){
        Controllers = new ArrayList<>();
        logger = Logger.CreatLogger();
        exportConverter = new ExportConverter();

    }
    @Override
    public void Export(String name, String typeOfExport) {
        try{
            var user = Application.getUserService().getUser(name);
            if(user != null){
                Util.setSkipValidation(true);
            }
            logger.NotifyAction(name , "Export data as "+typeOfExport);
            storegeService= new TextFileStorage(name);
            Controllers = ControllerFactory.CreateController(user.getUserType());
            FetchData(Controllers , name , storegeService);
            convertAndSave( name,  name,  typeOfExport);
           // exportConverter.convertToPDF(name,  name +".pdf");

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

    private void FetchData(List<Controller> Controllers , String name , StoregeService storegeService)  {
        for (Controller controller : Controllers) {
            controller.getData(name, storegeService);
        }
    }
    private void convertAndSave(String data, String name, String typeOfExport) {
        String pdfFileName = "PDF_File/" + name + ".pdf";
        String zipFileName = "ZIP_File/" + name + ".zip";

        if ("pdf".equals(typeOfExport)) {
            exportConverter.convertToPDF(data, pdfFileName);
        } else if ("zip".equals(typeOfExport)) {
            exportConverter.convertToPDF(data, pdfFileName);
            exportConverter.convertToZIP(pdfFileName, zipFileName);
        } else {
            System.err.println("Unsupported export type: " + typeOfExport);
        }
    }

}
