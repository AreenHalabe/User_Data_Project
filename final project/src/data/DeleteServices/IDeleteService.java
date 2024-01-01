package data.DeleteServices;

import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import iam.UserType;

public interface IDeleteService {
    public void Delete(String name ,String typedelete);
}
