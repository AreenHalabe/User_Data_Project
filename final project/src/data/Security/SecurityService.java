package data.Security;

import iam.IUserService;
import iam.UserType;

public abstract class  SecurityService {

   private UserType Usertype;

   public abstract boolean cheack(String name , String password);
   public UserType getUsertype(){
      return Usertype;
   };

   protected void setUserType(UserType userType){
      this.Usertype = userType;
   }


}
