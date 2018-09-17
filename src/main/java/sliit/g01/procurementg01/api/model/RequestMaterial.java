package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "RequestMaterial")
public class RequestMaterial {


	private String orderId;
	private String requestedPerson;
	private String siteId;
	private String requestedDate;
    private Map<String, Integer> item;
	private String isImmediated;
	private String isProcumentApproved;
	private String isSiteManagerApproved;
	private String siteManagerName;
	private String siteManagerID;
	
	public String getIsSiteManagerApproved() {
		return isSiteManagerApproved;
	}

	public void setIsSiteManagerApproved(String isSiteManagerApproved) {
		this.isSiteManagerApproved = isSiteManagerApproved;
	}

    public Map<String, Integer> getItem() {
        return item;
    }

    public void setItem(Map<String, Integer> item) {
        this.item = item;
    }
	
	public String getSiteManagerName() {
		return siteManagerName;
	}

	public void setSiteManagerName(String siteManagerName) {
		this.siteManagerName = siteManagerName;
	}

	public String getSiteManagerID() {
		return siteManagerID;
	}

	public void setSiteManagerID(String siteManagerID) {
		this.siteManagerID = siteManagerID;
	}

	
	
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
