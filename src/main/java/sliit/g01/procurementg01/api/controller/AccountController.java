package sliit.g01.procurementg01.api.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sliit.g01.procurementg01.api.model.Account;
import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Payment;
import sliit.g01.procurementg01.api.service.impl.AccountServiceImpl;
import sliit.g01.procurementg01.api.service.impl.AccountingStaffServiceImpl;

import java.util.List;

/**
 * @author Viraj
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountService;

	@RequestMapping(method = RequestMethod.POST)
	public Account createPayment(@Validated @RequestBody final Account account) {
		return accountService.addAccount(account);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountBySupplierId(@PathVariable("id") final String id) {
		return accountService.getAccountBySupplierId(id);
	}

}
