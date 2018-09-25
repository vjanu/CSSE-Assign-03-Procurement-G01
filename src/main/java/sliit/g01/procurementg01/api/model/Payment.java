package sliit.g01.procurementg01.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by viraj
 **/
@Document(collection = "Payment")
public class Payment {

	@Id
	private String paymentID;
	private String paymentMethod;
//	private String supplierId;
	private double totAmount;
	@JsonIgnore
	private String isDeleted;


	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

//	public String getSupplierId() {
//		return supplierId;
//	}
//
//	public void setSupplierId(String supplierId) {
//		this.supplierId = supplierId;
//	}

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
}
