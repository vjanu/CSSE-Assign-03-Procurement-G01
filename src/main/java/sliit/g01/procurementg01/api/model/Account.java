package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by viraj
 **/
@Document(collection = "Account")
public class Account {

	private String accNo;
	private String bankName;
	private String supplierId;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}



}
