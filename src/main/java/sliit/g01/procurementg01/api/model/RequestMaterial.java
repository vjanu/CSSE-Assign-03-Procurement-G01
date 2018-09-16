package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RequestMaterial")
public class RequestMaterial {

	private String orderId;
	private String requestedPerson;
	private String siteId;
	private String requestedDate;
	private String item;
	private String isImmediated;
	private String isProcumentApproved;
	// private String status;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRequestedPerson() {
		return requestedPerson;
	}

	public void setRequestedPerson(String requestedPerson) {
		this.requestedPerson = requestedPerson;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getIsImmediated() {
		return isImmediated;
	}

	public void setIsImmediated(String isImmediated) {
		this.isImmediated = isImmediated;
	}

	public String getIsProcumentApproved() {
		return isProcumentApproved;
	}

	public void setIsProcumentApproved(String isProcumentApproved) {
		this.isProcumentApproved = isProcumentApproved;
	}

}
