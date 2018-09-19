package sliit.g01.procurementg01.api.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.repository.AccountingStaffRepository;
import sliit.g01.procurementg01.api.service.AccountingStaffService;
/***
 * created by viraj
 ***/


@Service
public class AccountingStaffServiceImpl implements AccountingStaffService {

    @Autowired
    private AccountingStaffRepository accountingStaffRepository;

	@Override
	public AccountingStaff addAccountingStaff(AccountingStaff accountingStaff) {
		accountingStaff.setEmployeeId(UUID.randomUUID().toString());
		return accountingStaffRepository.save(accountingStaff);
	}

	@Override
	public List<AccountingStaff> listAccountingStaff() {
		return accountingStaffRepository.findAll();
	}

	@Override
	public AccountingStaff updateAccountingStaff(String id, AccountingStaff accountingStaff) {
		return accountingStaffRepository.save(accountingStaff);

	}

	@Override
	public void deleteAccountingStaff(String id) {
//		 final AccountingStaff accountStaff = accountingStaffRepository.findOne(id);
//		 accountStaff.setDeleted(true);
//	     accountingStaffRepository.save(accountStaff);
		
	}

	@Override
	public List<Item> listItemDetails(String itemId) {
//		return accountingStaffRepository.findOne(itemId);
		return null;
	}

	@Override
	public AccountingStaff getAccountingStaff(String id) {
//		return accountingStaffRepository.findOne(id);
		return null;
	}

	
}
