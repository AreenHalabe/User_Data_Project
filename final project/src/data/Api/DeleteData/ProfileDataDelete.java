package data.Api.DeleteData;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import iam.UserProfile;

public class ProfileDataDelete implements Icontroller{
    @Override
    public void Delete(String username) throws SystemBusyException, NotFoundException, BadRequestException {
        // delete user profile data
        UserProfile userProfile = Application.getUserService().getUser(username);
        Application.getUserService().deleteUser(username);
    }
}
