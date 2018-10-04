package sliit.g01.procurementg01.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Login;
import sliit.g01.procurementg01.api.repository.LoginRepository;
import sliit.g01.procurementg01.api.service.LoginService;



@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loggedUserRepository;

	@Override
	public Login addUsers(Login login) {
		// TODO Auto-generated method stub
		return loggedUserRepository.save(login);
	}
	
	
}
