package data.Userdata;

import data.Application;
import data.DeleteDataServices.DeleteServices;
import data.DeleteDataServices.IDeleteService;
import data.Exception.BadDeleteRequstException;
import data.Exception.BadExportRequestException;
import data.Exception.BadOperationRequstException;
import data.ExportDataServices.ExportServices;
import data.ExportDataServices.IExportServices;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import exceptions.Util;

import java.time.Instant;
import java.util.Scanner;

public class UserData {
    private IDeleteService deleteService;

    private IExportServices exportServices;


    public void Deletedata(String typeDelete){
        Scanner scanner = new Scanner(System.in);
        while (true){
        try{
            if(typeDelete.equals("soft") || typeDelete.equals("hard")){
                deleteService = new DeleteServices();
                String name = Application.getLoginUserName();
                deleteService.Delete(name , typeDelete);
                break;
            }
            else {
                throw new BadDeleteRequstException("Wrong type of Delete please Enter one of the options (soft) or (hard)");
            }
        }
        catch (BadDeleteRequstException e){
            System.err.println(e.getMessage());
            typeDelete = scanner.nextLine();
        }
        }

    }



    private void ExportData(String typeOfExport)  {
        Scanner scanner = new Scanner(System.in);
            while (true){
                try{
                    if(typeOfExport.equals("pdf")||typeOfExport.equals("zip")){
                        exportServices = new ExportServices();
                        String name = Application.getLoginUserName();
                        exportServices.Export(name , typeOfExport);
                        break;
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
        String name = Application.getLoginUserName();
        if(ValidateName(name)){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to user data feature you can make export or delete your data");
            System.out.println("enter (export) or (delete)");
            while (true){
                String operation = scanner.nextLine();

                try{
                    if (operation.equals("export")) {
                        System.out.println("What format do you want ? enter (pdf) or (zip)");
                        String typeOfExport = scanner.nextLine();
                        ExportData(typeOfExport);
                        break;
                    } else if (operation.equals("delete")) {
                        System.out.println("What is the deletion method? ? enter (soft) or (hard)");
                        String typeOfDelete = scanner.nextLine();
                        Deletedata(typeOfDelete);
                        break;
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


    private boolean ValidateName(String name){
        if(Instant.now().getEpochSecond()%3==0){
            try {
                // Waiting for 1 seconds (1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Util.validateUserName(name);
            return true;
        }
        catch (BadRequestException e){
            System.err.println(e.getMessage());
        }
        catch (SystemBusyException e){
            System.err.println(e.getMessage());
        }
        return false;
    }





}
