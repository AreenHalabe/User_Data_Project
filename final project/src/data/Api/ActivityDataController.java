package data.Api;

import data.Application;
import data.FileStorag.StoregeService;
import data.Userdata.UserData;

public class ActivityDataController implements Controller{
    @Override
    public void getData(String name, StoregeService storegeService) {
        var ActivityList = Application.getUserActivityService().getUserActivity(name);
        String data="";
        for (var activity:ActivityList) {
            data = activity.getId() + " , "+ activity.getActivityType() + " , "+ activity.getActivityDate();
            storegeService.uploadData(data);
        }
    }
}
