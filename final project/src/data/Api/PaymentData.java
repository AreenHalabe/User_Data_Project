package data.Api;

import data.FileStorag.StoregeService;
import data.Userdata.UserData;

public class PaymentData implements Controller{
    @Override
    public void getData(String name, StoregeService storegeService) {
        var TransactionList = UserData.getiPayment().getTransactions(name);
        String data="";
        for (var item: TransactionList) {
            //System.out.println(item);
            data = item.getId() + " , "+ item.getAmount() + " , "+item.getDescription();
            storegeService.uploadData(data);

        }

    }
}
