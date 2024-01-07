package data.DeleteDataServices;

public interface IDeleteService {
    /**
     * Deletes data based on name and delete type.
     *
     * @param name the name of the data to delete
     * @param typedelete the type of delete (hard/soft)
     */
    public void Delete(String name ,String typedelete);
}
