package data.UserService;

import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

public interface DeleteService {
    public void Delete(String username) throws SystemBusyException, NotFoundException, BadRequestException;
}
