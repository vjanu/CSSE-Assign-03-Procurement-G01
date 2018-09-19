package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.service.AccountingStaffService;
/***
 * created by viraj
 ***/
@RestController
@RequestMapping("/employee/accounting-staff")
public class AccountingStaffController {
	 @Autowired
	    private AccountingStaffService accountingStaffService;

	 @RequestMapping(method = RequestMethod.POST)
	    public AccountingStaff createAccountingStaff(@Validated @RequestBody final AccountingStaff accountingStaff) {
	        return accountingStaffService.addAccountingStaff(accountingStaff);
	    }
	 @RequestMapping(method = RequestMethod.GET)
	    public List<AccountingStaff> retrieveAlAccountingStaff() {
	        return accountingStaffService.listAccountingStaff();
	    }
	    
	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public AccountingStaff updateAccountingStaff(@PathVariable("id") final String id, @Validated @RequestBody final AccountingStaff accountingStaff) {
	        return accountingStaffService.updateAccountingStaff(id, accountingStaff);
	    }

	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public AccountingStaff getAccountingStaff(@PathVariable("id") final String id) {
	        return accountingStaffService.getAccountingStaff(id);
	    }

	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public void deleteAccountingStaff(@PathVariable("id") final String id) {
	    	accountingStaffService.deleteAccountingStaff(id);
	    }
}
