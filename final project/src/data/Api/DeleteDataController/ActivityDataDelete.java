package data.Api.DeleteDataController;

import activity.UserActivity;
import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

import java.util.ArrayList;
import java.util.List;

public class ActivityDataDelete  implements IDeleteDataController {
    /**
     * Deletes the user's activity data from services.
     *
     * @param username the user's username
     * @throws BadRequestException if invalid request
     * @throws NotFoundException if user not found
     * @throws SystemBusyException if system is busy
     */
    @Override
    public void DeleteDataFromServices(String username)  {
        try {
            List<UserActivity> activities = Application.getUserActivityService().getUserActivity(username);
            List<UserActivity> copyOfactivities = new ArrayList<>(activities);

            for (UserActivity activity : copyOfactivities)
                Application.getUserActivityService().removeUserActivity(username, activity.getId());

        }
        catch (RuntimeException e){
            System.err.println("Something wrong try again");
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
        System.out.println("Deleted Successfully from Activity");
    }
}
