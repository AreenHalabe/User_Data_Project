package data.Api.FeatchDataController;


import iam.UserType;
import java.util.ArrayList;
import java.util.List;

public class ControllerFactory {

    public static final List<FetchDataController> CreateController(UserType type){
         List<FetchDataController> fetchDataControllerList = new ArrayList<>();
        fetchDataControllerList.add(new ProfileFetchDataController());
        fetchDataControllerList.add(new PostFetchDataController());

        if (UserType.REGULAR_USER.equals(type)) {
            fetchDataControllerList.add(new ActivityFetchDataController());
        }
        else if (UserType.PREMIUM_USER.equals(type)) {
            fetchDataControllerList.add(new ActivityFetchDataController());
            fetchDataControllerList.add(new PaymentFetchDataController());
        }

        return fetchDataControllerList;
    }
}
