package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by viraj
 **/
@Document(collection = "Rating")
public class Rating {

	private String purchaseOrderReference;
	private String supplierName;
	private String supplierId;
	private String constructorId;
	private String constructorName;
	private int deliveryEfficiency;
	private int supportiveness;
	private int workOnTime;
	private int overallRate;
	private String feedback;


	public String getConstructorId() {
		return constructorId;
	}

	public void setConstructorId(String constructorId) {
		this.constructorId = constructorId;
	}

	public String getPurchaseOrderReference() {
		return purchaseOrderReference;
	}

	public void setPurchaseOrderReference(String purchaseOrderReference) {
		this.purchaseOrderReference = purchaseOrderReference;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getConstructorName() {
		return constructorName;
	}

	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}

	public int getDeliveryEfficiency() {
		return deliveryEfficiency;
	}

	public void setDeliveryEfficiency(int deliveryEfficiency) {
		this.deliveryEfficiency = deliveryEfficiency;
	}

	public int getSupportiveness() {
		return supportiveness;
	}

	public void setSupportiveness(int supportiveness) {
		this.supportiveness = supportiveness;
	}

	public int getWorkOnTime() {
		return workOnTime;
	}

	public void setWorkOnTime(int workOnTime) {
		this.workOnTime = workOnTime;
	}

	public int getOverallRate() {
		return overallRate;
	}

	public void setOverallRate(int overallRate) {
		this.overallRate = overallRate;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}




}
