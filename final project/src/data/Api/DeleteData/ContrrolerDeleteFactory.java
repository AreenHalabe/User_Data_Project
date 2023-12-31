package data.Api.DeleteData;

import data.Exception.BadDeleteRequstException;
import iam.UserType;

import java.util.ArrayList;
import java.util.List;

public class ContrrolerDeleteFactory {
   public static List<Icontroller> CreateController(String typeDelete, UserType userType){
       List<Icontroller> controllers= new ArrayList<>();
       try {
           if(typeDelete.equals("hard")){
               controllers.add(new PostDataDelete());
               controllers.add(new ProfileDataDelete());
               if(UserType.REGULAR_USER.equals(userType)){
                   controllers.add(new ActivityDataDelete());
               } else if (UserType.PREMIUM_USER.equals(userType)) {
                   controllers.add(new ActivityDataDelete());
                   controllers.add(new PaymentDataDelete());
               }
               return controllers;
           } else if (typeDelete.equals("soft")) {
               controllers.add(new PostDataDelete());
               if(UserType.REGULAR_USER.equals(userType)){
                   controllers.add(new ActivityDataDelete());
               } else if (UserType.PREMIUM_USER.equals(userType)) {
                   controllers.add(new ActivityDataDelete());
                   controllers.add(new PaymentDataDelete());
               }
               return controllers;
           } else {
               throw new BadDeleteRequstException("Wrong type of Delete please chose (soft) or (hard)");
           }

       }
       catch (BadDeleteRequstException e){
           System.err.println(e.getMessage());
       }
       return controllers;
   }
}
