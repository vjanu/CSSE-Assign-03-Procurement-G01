package sliit.g01.procurementg01.api.service;

import java.util.HashMap;

import sliit.g01.procurementg01.api.model.Login;

public interface LoginService {

	Login addUsers(Login login);

	HashMap<String, String> validateUser(Login login);

}
