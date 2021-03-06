package sliit.g01.procurementg01.api.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Login;
import sliit.g01.procurementg01.api.repository.LoginRepository;
import sliit.g01.procurementg01.api.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loggedUserRepository;

	@Autowired
	private SiteManagerServiceImpl siteManagerServiceImpl;

	@Autowired
	private AccountingStaffServiceImpl accountingStaffServiceImpl;

	@Autowired
	private SupplierServiceImpl supplierServiceImpl;

	@Autowired
	private ConstructorServiceImpl constructorServiceImpl;

	@Override
	public Login addUsers(Login login) {
		return loggedUserRepository.save(login);
	}

	public Login getUser(String username, String password) {
		return loggedUserRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public HashMap<String, String> validateUser(Login login) {

		HashMap<String, String> userInfo = new HashMap<String, String>();
		System.out.println(login.getUsername());
		System.out.println(login.getPassword());

		if (this.getUser(login.getUsername(), login.getPassword()).getUserType() != null) {
			userInfo.put("success", "true");
			userInfo.put("userType", this.getUser(login.getUsername(), login.getPassword()).getUserType());
			userInfo.put("userId", this.getUser(login.getUsername(), login.getPassword()).getUsername());
		}

		else if (siteManagerServiceImpl.getSiteManager(login.getUsername(), login.getPassword())
				.getEmployeeId() != null) {
			userInfo.put("success", "true");
			userInfo.put("userType", "Sitemanager");
			userInfo.put("userId",
					siteManagerServiceImpl.getSiteManager(login.getUsername(), login.getPassword()).getEmployeeId());
		} else if (accountingStaffServiceImpl.getAccountingStaff(login.getUsername(), login.getPassword())
				.getEmployeeId() != null) {
			userInfo.put("success", "true");
			userInfo.put("userType", "Accounting-Staff");
			userInfo.put("userId", accountingStaffServiceImpl
					.getAccountingStaff(login.getUsername(), login.getPassword()).getEmployeeId());
		}

		else if (supplierServiceImpl.getSupplier(login.getUsername(), login.getPassword()).getSupplierId() != null) {
			userInfo.put("success", "true");
			userInfo.put("userType", "Supplier");
			userInfo.put("userId",
					supplierServiceImpl.getSupplier(login.getUsername(), login.getPassword()).getSupplierId());
		}

		else if (constructorServiceImpl.getConstructor(login.getUsername(), login.getPassword())
				.getEmployeeId() != null) {
			userInfo.put("success", "true");
			userInfo.put("userType", "Constructor");
			userInfo.put("userId",
					constructorServiceImpl.getConstructor(login.getUsername(), login.getPassword()).getEmployeeId());
		}
		return userInfo;
	}

}
