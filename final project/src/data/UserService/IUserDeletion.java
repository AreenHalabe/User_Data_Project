package data.UserService;


public interface IUserDeletion {
    public void softDelete(String username);

    public void hardDelete(String username);
}
