package data.Security;

import iam.IUserService;
import iam.UserType;

public interface  SecurityService {




   abstract public boolean cheack(String name , String password);
   public UserType getUsertype();


}
