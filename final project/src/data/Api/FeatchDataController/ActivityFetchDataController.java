package data.Api.FeatchDataController;

import data.Application;
import data.FileStorag.StoregeService;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

public class ActivityFetchDataController implements FetchDataController {
    @Override
    public void getData(String name, StoregeService storegeService)  {
        try{
            var ActivityList = Application.getUserActivityService().getUserActivity(name);
            String data="";
            for (var activity:ActivityList) {
                data = activity.getId() + " , "+ activity.getActivityType() + " , "+ activity.getActivityDate();
                storegeService.uploadData(data);
            }
            System.out.println("upload (Activity_Data) successfully.");
        }

         catch (BadRequestException e){
            System.err.println(e.getMessage());
        }
        catch (NotFoundException e){
            System.err.println(e.getMessage());
        }
        catch (SystemBusyException e){
            System.err.println(e.getMessage());
        }
    }
}
