package data.Api.DeleteData;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import payment.Transaction;

import java.util.List;

public class PaymentDataDelete implements Icontroller{
    @Override
    public void Delete(String username)  {
        try {
            // delete user payment services
            List<Transaction> transactions = Application.getPaymentService().getTransactions(username);
            for (Transaction transaction : transactions)
                Application.getPaymentService().removeTransaction(username, transaction.getId());
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
