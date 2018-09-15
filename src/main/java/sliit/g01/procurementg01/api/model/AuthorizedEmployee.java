package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;

/*
 * NOTE: This this is the parent class do not create a mongo repository for this.
 *       We only create mongo repositories for all the child classes.
 *       But make sure to implement an empty constructor and all getters and setters in,
 *       all the classes.
 */
public class AuthorizedEmployee {

	@Id
	private String employeeId;
	private String employeeType;
	private String employeeName;
	private String address;
	private String email;
	private String phone;

	public void addComment(String comment) {
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
