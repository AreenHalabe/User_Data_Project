package data.Api;

import data.Application;
import data.FileStorag.StoregeService;
import data.Userdata.UserData;

public class PaymentDataController implements Controller{
    @Override
    public void getData(String name, StoregeService storegeService) {
        var TransactionList = Application.getPaymentService().getTransactions(name);

        String data="";
        for (var item: TransactionList) {
            data = item.getId() + " , "+ item.getAmount() + " , "+item.getDescription();
            storegeService.uploadData(data);

        }

    }
}
