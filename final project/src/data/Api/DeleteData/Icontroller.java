package data.Api.DeleteData;

import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

public interface Icontroller {
    public void Delete(String username);
}
