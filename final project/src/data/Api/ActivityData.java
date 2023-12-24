package data.Api;

import data.FileStorag.StoregeService;
import data.Userdata.UserData;

public class ActivityData implements Controller{
    @Override
    public void getData(String name, StoregeService storegeService) {
        var ActivityList = UserData.getiUserActivityService().getUserActivity(name);
        String data="";
        for (var activity:ActivityList) {
            data = activity.getId() + " , "+ activity.getActivityType() + " , "+ activity.getActivityDate();
            storegeService.uploadData(data);
        }
    }
}
