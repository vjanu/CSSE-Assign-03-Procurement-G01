package sliit.g01.procurementg01.api.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "RequestMaterial")
public class RequestMaterial {

	@Id
	private String requestId;
	private String requestedPerson;
	private String siteId;
	private String quantity;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRequestingDate() {
		return requestingDate;
	}

	public void setRequestingDate(String requestingDate) {
		this.requestingDate = requestingDate;
	}

	private String requestingDate;
	private String requestedDate;
	private Map<String, String> items;
	private String isImmediated;
	private Boolean isProcumentApproved;
	private String isSiteManagerApproved;
	private String siteManagerName;
	private String siteManagerID;
	@JsonIgnore
	private String isDeclinedBySiteManager;

	public String getIsDeclinedBySiteManager() {
		return isDeclinedBySiteManager;
	}

	public void setIsDeclinedBySiteManager(String isDeclinedBySiteManager) {
		this.isDeclinedBySiteManager = isDeclinedBySiteManager;
	}

	public String getIsSiteManagerApproved() {
		return isSiteManagerApproved;
	}

	public void setIsSiteManagerApproved(String isSiteManagerApproved) {
		this.isSiteManagerApproved = isSiteManagerApproved;
	}

	public Map<String, String> getItem() {
		return items;
	}

	public void setItem(Map<String, String> items) {
		this.items = items;
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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	public String getIsImmediated() {
		return isImmediated;
	}

	public void setIsImmediated(String isImmediated) {
		this.isImmediated = isImmediated;
	}

	public Boolean getIsProcumentApproved() {
		return isProcumentApproved;
	}

	public void setIsProcumentApproved(Boolean isProcumentApproved) {
		this.isProcumentApproved = isProcumentApproved;
	}

}
