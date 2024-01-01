package data.Api.DeleteDataController;

import activity.UserActivity;
import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

import java.util.List;

public class ActivityDataDelete  implements  Icontroller{
    @Override
    public void DeleteDataFromServices(String username)  {
        try {
            List<UserActivity> activities = Application.getUserActivityService().getUserActivity(username);
            for (UserActivity activity : activities)
                Application.getUserActivityService().removeUserActivity(username, activity.getId());
            System.out.println("Deleted Successfully from Activity");
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
