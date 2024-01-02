package data.Api.FeatchDataController;


import iam.UserType;
import java.util.ArrayList;
import java.util.List;

public class ControllerFactory {

    public static final List<Controller> CreateController(UserType type){
         List<Controller> controllerList = new ArrayList<>();
        controllerList.add(new ProfileDataController());
        controllerList.add(new PostDataController());

        if (UserType.REGULAR_USER.equals(type)) {
            controllerList.add(new ActivityDataController());
        }
        else if (UserType.PREMIUM_USER.equals(type)) {
            controllerList.add(new ActivityDataController());
            controllerList.add(new PaymentDataController());
        }

        return controllerList;
    }
}
