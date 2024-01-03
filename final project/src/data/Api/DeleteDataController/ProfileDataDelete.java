package data.Api.DeleteDataController;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import iam.UserProfile;

public class ProfileDataDelete implements IDeleteDataController {
    /**
     * Deletes user profile data from services.
     *
     * @param username the users username
     * @throws BadRequestException if invalid request
     * @throws NotFoundException if user not found
     * @throws SystemBusyException if system is busy
     */
    @Override
    public void DeleteDataFromServices(String username)  {
        try {
            // delete user profile data
            UserProfile userProfile = Application.getUserService().getUser(username);
            Application.getUserService().deleteUser(username);
            System.out.println("Deleted Successfully from Profile");
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
