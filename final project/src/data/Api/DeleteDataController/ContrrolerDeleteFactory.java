package data.Api.DeleteDataController;

import data.Exception.BadDeleteRequstException;
import iam.UserType;

import java.util.ArrayList;
import java.util.List;

public class ContrrolerDeleteFactory {
    /**
     * Creates a list of delete controllers based on delete type and user type
     *
     * @param typeDelete the type of delete (soft/hard)
     * @param userType the type of user
     * @return a list of controllers that handle delete operations
     * @throws BadDeleteRequstException if invalid delete type
     */
   public static List<IDeleteDataController> CreateController(String typeDelete, UserType userType){
       List<IDeleteDataController> controllers= new ArrayList<>();
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
               throw new BadDeleteRequstException("Wrong type of Delete please Enter one of the options (soft) or (hard)");
           }

       }
       catch (BadDeleteRequstException e){
           System.err.println(e.getMessage());
       }
       return controllers;
   }
}
