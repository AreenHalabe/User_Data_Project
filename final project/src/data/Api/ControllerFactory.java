package data.Api;

import data.Userdata.UserData;
import iam.UserType;

import java.util.ArrayList;
import java.util.List;

public class ControllerFactory {

    public static final List<Controller> CreateController(UserType type){
         List<Controller> controllerList = new ArrayList<>();
        controllerList.add(new ProfileData());
        controllerList.add(new PostData());

        if ("REGULAR_USER".equals(type)) {
            controllerList.add(new ActivityData());
        }
        else if ("PREMIUM_USER".equals(type)) {
            controllerList.add(new ActivityData());
            controllerList.add(new PaymentData());
        }

        return controllerList;
    }
}
