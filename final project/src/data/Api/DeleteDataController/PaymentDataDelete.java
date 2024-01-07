package data.Api.DeleteDataController;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import payment.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PaymentDataDelete implements IDeleteDataController {
    /**
     * Deletes user payment data from services.
     *
     * @param username the users username
     * @throws BadRequestException if invalid request
     * @throws NotFoundException if user not found
     * @throws SystemBusyException if system is busy
     */
    @Override
    public void DeleteDataFromServices(String username)  {
        try {
            List<Transaction> transactions = Application.getPaymentService().getTransactions(username);
            List<Transaction> copyOftransactions = new ArrayList<>(transactions);

            for (Transaction transaction : copyOftransactions)
                Application.getPaymentService().removeTransaction(username, transaction.getId());

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

        System.out.println("Deleted Successfully from Payment");
    }
}
