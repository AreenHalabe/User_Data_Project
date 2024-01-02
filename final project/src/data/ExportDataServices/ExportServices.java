package data.ExportDataServices;

import data.Api.FeatchDataController.FetchDataController;
import data.Api.FeatchDataController.ControllerFactory;
import data.Application;
import data.Converter.ExportZIPConverter;
import data.Converter.ExportPDFConverter;
import data.FileStorag.StoregeService;
import data.FileStorag.TextFileStorage;
import data.Loggers.Logger;
import data.Loggers.Loggers;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import exceptions.Util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ExportServices implements IExportServices{
    private StoregeService storegeService;
    private List<FetchDataController> fetchDataControllers;
    private Loggers logger;
    private ExportPDFConverter exportPDFConverter;
    private ExportZIPConverter exportZIPConverter;

    public ExportServices(){
        fetchDataControllers = new ArrayList<>();
        logger = Logger.CreatLogger();
        exportPDFConverter = new ExportPDFConverter();
        exportZIPConverter = new ExportZIPConverter();

    }
    @Override
    public void Export(String name, String typeOfExport) {
        if(Instant.now().getEpochSecond()%3==0){
            try {
                // Waiting for 1 seconds (1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            var user = Application.getUserService().getUser(name);
            if(user != null){
                Util.setSkipValidation(true);
            }
            logger.NotifyAction("Export data as "+typeOfExport);
            storegeService= new TextFileStorage(name);
            fetchDataControllers = ControllerFactory.CreateController(user.getUserType());
            FetchData(fetchDataControllers, name , storegeService);
            convertAndSave( name,  name,  typeOfExport);
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

    private void FetchData(List<FetchDataController> fetchDataControllers, String name , StoregeService storegeService)  {
        for (FetchDataController fetchDataController : fetchDataControllers) {
            fetchDataController.getData(name, storegeService);
        }
    }
    private void convertAndSave(String data, String name, String typeOfExport) {
        String pdfFileName = "PDF_File/" + name + ".pdf";
        String zipFileName = "ZIP_File/" + name + ".zip";

        try {
            if ("pdf".equals(typeOfExport)) {
                exportPDFConverter.convertToPDF(data, pdfFileName);

            } else if ("zip".equals(typeOfExport)) {
                exportPDFConverter.convertToPDF(data, pdfFileName);
                exportZIPConverter.convertToZIP(pdfFileName, zipFileName);

            } else {
                throw new BadRequestException("Unsupported export type: " + typeOfExport);
            }
        } catch (Exception e) {
            System.err.println("Error during export: " + e.getMessage());
        }
    }

}



