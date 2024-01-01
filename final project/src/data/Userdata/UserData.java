package data.Userdata;

import data.Application;
import data.DeleteDataServices.DeleteServices;
import data.DeleteDataServices.IDeleteService;
import data.Exception.BadDeleteRequstException;
import data.Exception.BadExportRequestException;
import data.Exception.BadOperationRequstException;
import data.ExportDataServices.ExportServices;
import data.ExportDataServices.IExportServices;

import java.util.Scanner;

public class UserData {
    private IDeleteService deleteService;

    private IExportServices exportServices;


    public void Deletedata(String typeDelete){
        try{
            if(typeDelete.equals("soft") || typeDelete.equals("hard")){
                deleteService = new DeleteServices();
                String name = Application.getLoginUserName();
                deleteService.Delete(name , typeDelete);
            }
            else {
                throw new BadDeleteRequstException("Wrong type of Delete please Enter one of the options (soft) or (hard)");
            }
        }
        catch (BadDeleteRequstException e){
            System.err.println(e.getMessage());
        }

    }



    public void ExportData(String typeOfExport)  {
        Scanner scanner = new Scanner(System.in);
            while (true){
                try{
                    if(typeOfExport.equals("pdf")||typeOfExport.equals("zip")){
                        exportServices = new ExportServices();
                        String name = Application.getLoginUserName();
                        exportServices.Export(name , typeOfExport);
                    }
                    else {
                        throw new BadExportRequestException("Wrong type of format entered . please enter one of the option (pdf) or (zip)");

                    }
                }
                catch (BadExportRequestException e){
                    System.err.println(e.getMessage());
                    typeOfExport = scanner.nextLine();

                }
            }

    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to user data feature you can make export or delete data");
        System.out.println("enter (export) or (delete)");
        while (true){
            String operation = scanner.nextLine();

            try{
                if (operation.equals("export")) {
                    System.out.println("What format do you want ? enter (pdf) or (zip)");
                    String typeOfExport = scanner.nextLine();
                    ExportData(typeOfExport);
                } else if (operation.equals("delete")) {
                    System.out.println("What is the deletion method? ? enter (soft) or (hard)");
                    String typeOfExport = scanner.nextLine();
                    ExportData(typeOfExport);
                } else {
                    throw new BadOperationRequstException("not valid operation. please enter (export) or (delete)");
                }
            }
            catch (BadOperationRequstException e){
                System.err.println(e.getMessage());
            }
        }






    }





}
