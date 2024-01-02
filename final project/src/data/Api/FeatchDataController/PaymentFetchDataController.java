package data.Api.FeatchDataController;

import data.Application;
import data.FileStorag.StoregeService;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

public class PaymentFetchDataController implements FetchDataController {
    @Override
    public void getData(String name, StoregeService storegeService)  {
        try {
            var TransactionList = Application.getPaymentService().getTransactions(name);
            String data="";
            for (var item: TransactionList) {
                data = item.getId() + " , "+ item.getAmount() + " , "+item.getDescription();
                storegeService.uploadData(data);
            }
            System.out.println("upload (Payment_Data) successfully.");
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