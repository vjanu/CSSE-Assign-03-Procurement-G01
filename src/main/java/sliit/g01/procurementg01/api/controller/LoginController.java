package sliit.g01.procurementg01.api.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Login;
import sliit.g01.procurementg01.api.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/addemployee")
	public Login addUsers(@Validated @RequestBody Login login) {
		login.setEmployeeId("CS" + RandomStringUtils.randomNumeric(5));
		return loginService.addUsers(login);
	}

}
