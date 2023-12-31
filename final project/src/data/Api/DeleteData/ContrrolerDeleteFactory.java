package data.Api.DeleteData;

import data.UserService.HardDelete;

import java.util.ArrayList;
import java.util.List;

public class ContrrolerDeleteFactory {
   public static List<Icontroller> CreateController(String typeDelete){
       List<Icontroller> controllers= new ArrayList<>();
       if(typeDelete.equals("Hard Delete")){
         controllers.add(new ActivityDataDelete());
         controllers.add(new PostDataDelete());
         controllers.add(new PaymentDataDelete());
         controllers.add(new ProfileDataDelete());
         
       } else if (typeDelete.equals("Soft Delete")) {
           controllers.add(new ActivityDataDelete());
           controllers.add(new PostDataDelete());
           controllers.add(new PaymentDataDelete());
       }
       return controllers;
   }
}
