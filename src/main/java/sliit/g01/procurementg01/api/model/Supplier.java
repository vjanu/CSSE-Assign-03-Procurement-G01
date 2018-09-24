package sliit.g01.procurementg01.api.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Supplier")
public class Supplier {
    private String supplierId;
    private String supplierName;
    private int bankAccountNo;
    private String address;
    private String email;
    private String phone;
    private boolean availability;
	
    // setters
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	// Getters
	public String getSupplierId() {
		return supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public int getBankAccountNo() {
		return bankAccountNo;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public boolean isAvailability() {
		return availability;
	}
}
