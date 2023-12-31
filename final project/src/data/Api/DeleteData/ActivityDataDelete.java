package data.Api.DeleteData;

import activity.UserActivity;
import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

import java.util.List;

public class ActivityDataDelete  implements  Icontroller{
    @Override
    public void Delete(String username) throws SystemBusyException, NotFoundException, BadRequestException {
        // delete user activity services
        List<UserActivity> activities = Application.getUserActivityService().getUserActivity(username);
        for (UserActivity activity : activities)
            Application.getUserActivityService().removeUserActivity(username, activity.getId());
    }
}
