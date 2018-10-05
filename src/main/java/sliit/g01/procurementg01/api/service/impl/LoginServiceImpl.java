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
	private SiteManagerServiceImpl siteManagerImpl;

	@Override
	public Login addUsers(Login login) {
		// TODO Auto-generated method stub
		return loggedUserRepository.save(login);
	}

	@Override
	public HashMap<String, String> validateUser(Login login) {

		HashMap<String, String> userInfo = new HashMap<String, String>();
		System.out.println(login.getUsername());
		System.out.println(login.getPassword());

		if (login.getUsername().contentEquals("test") && login.getPassword().contentEquals("123")) {
			userInfo.put("success", "true");
			userInfo.put("userType", "Procurement-Staff");
			userInfo.put("userId", "");
		}

		// siteManagerImpl.

		return userInfo;
	}

}
