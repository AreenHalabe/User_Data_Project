package data.Api.DeleteDataController;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import payment.Transaction;
import posts.Post;

import java.util.ArrayList;
import java.util.List;

public class PaymentDataDelete implements Icontroller{
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
