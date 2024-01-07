package data.Userdata;

import data.Api.FeatchDataController.ControllerFactory;
import data.Application;
import data.DeleteDataServices.DeleteServices;
import data.DeleteDataServices.IDeleteService;
import data.Exception.BadDeleteRequstException;
import data.Exception.BadExportRequestException;
import data.Exception.BadOperationRequstException;
import data.ExportDataServices.ExportServices;
import data.ExportDataServices.IExportServices;
import data.FileStorag.TextFileStorage;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import exceptions.Util;
import iam.UserProfile;

import java.time.Instant;
import java.util.Scanner;

public class UserData {
    private IDeleteService deleteService;

    private IExportServices exportServices;



    private static String name;

    private static UserProfile user;


    public UserData(){
       this.name= Application.getLoginUserName();
    }

    /**
     * Deletes consumer records based totally on delete kind.
     *
     * @param typeDelete the kind of delete (smooth/tough)
     * @throws BadDeleteRequstException if invalid delete type
     */
    public void Deletedata(String typeDelete){
        Scanner scanner = new Scanner(System.in);
        while (true){
        try{
            if(typeDelete.equals("soft") || typeDelete.equals("hard")){
                deleteService = new DeleteServices();
                deleteService.Delete(this.name , typeDelete);
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
                        exportServices.Export(this.name , typeOfExport);
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
        Scanner scanner = new Scanner(System.in);
        if(checkUser()){
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


    private boolean ValidateName(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            if(Instant.now().getEpochSecond()%3==0){
                try {
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
                 this.name = scanner.nextLine();
            }
            catch (SystemBusyException e){
                System.err.println(e.getMessage());
                this.name = scanner.nextLine();
            }
        }

    }

    private boolean checkUser(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            ValidateName();
            if(Instant.now().getEpochSecond()%3==0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try{
                var user = Application.getUserService().getUser(this.name);
                if(user != null){
                    Util.setSkipValidation(true);
                    setUser(user);
                    return true;
                }

            }
            catch (BadRequestException e){
                System.err.println(e.getMessage());
                this.name = scanner.nextLine();
            }
            catch (NotFoundException e){
                System.err.println(e.getMessage());
                this.name = scanner.nextLine();
            }
            catch (SystemBusyException e){
                System.err.println(e.getMessage());
                this.name = scanner.nextLine();
            }

        }

    }

    public static UserProfile getUser(){
        return user;
    }

    private void setUser(UserProfile user){
        this.user = user;
    }
    public static String getName() {
        return name;
    }

}
