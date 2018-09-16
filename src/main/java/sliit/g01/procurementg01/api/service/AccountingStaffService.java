package sliit.g01.procurementg01.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
/***
 * created by viraj
 ***/


public interface AccountingStaffService {

	AccountingStaff addAccountingStaff(AccountingStaff accountingStaff);
	
	AccountingStaff getAccountingStaff(String id);

    List<AccountingStaff> listAccountingStaff();

    AccountingStaff updateAccountingStaff(String id, AccountingStaff accountingStaff);
    
    void deleteAccountingStaff(String id);
    
    List<Item> listItemDetails(String itemId);//temporary id check later
    
//    Boolean orderDeliveredStatus(Order orderId); //uncomment after order is created
    
//    List<Payment> listHoldPaymentDetails(Payment payment);
    
//    Boolean makePayments(Order orderId, Payment payment);
    
}
