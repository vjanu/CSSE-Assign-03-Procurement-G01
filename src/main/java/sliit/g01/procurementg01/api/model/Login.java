package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LoggedUsers")
public class Login {

	private String username;
	private String password;
	private String userType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
