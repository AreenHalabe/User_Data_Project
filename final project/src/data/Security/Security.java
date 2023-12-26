package data.Security;

import data.Application;
import data.Userdata.UserData;
import iam.IUserService;
import iam.UserType;

public class Security implements SecurityService{

    private UserType Usertype;


    @Override
    public UserType getUsertype() {
        return Usertype;
    }

    @Override
    public boolean cheack(String name, String password) {
        var ob = Application.getUserService().getUser(name);

        if (ob == null) {
            throw new NullPointerException("User not found for name: " + name);

        }

        else{
            if (name.equals(ob.getUserName()) && password.equals(ob.getPassword())) {
                Usertype = ob.getUserType();
                return true;
            }

            else{
                return false;
            }

        }

    }
}
