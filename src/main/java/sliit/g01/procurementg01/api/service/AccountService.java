package sliit.g01.procurementg01.api.service;

import sliit.g01.procurementg01.api.model.Account;
import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;

import java.util.List;

/**
 * @author Viraj
 */


public interface AccountService {


    Account getAccountBySupplierId(String supplierId);

    Account addAccount(Account account);

}
