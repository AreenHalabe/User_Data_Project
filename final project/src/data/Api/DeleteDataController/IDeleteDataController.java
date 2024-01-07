package data.Api.DeleteDataController;

public interface IDeleteDataController {

    /**
     * Deletes user data from different services
     *
     * @param username the username of the user to delete data for
     */
    public void DeleteDataFromServices(String username);
}
