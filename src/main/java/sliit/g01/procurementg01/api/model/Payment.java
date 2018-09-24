package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by viraj
 **/
@Document(collection = "Payment")
public class Payment {

	private String paymentID;
	private String paymentMethod;
	private String supplierId;
	private double totAmount;

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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
}
