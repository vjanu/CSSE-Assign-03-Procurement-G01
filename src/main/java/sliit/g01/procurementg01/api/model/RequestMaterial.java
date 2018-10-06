package sliit.g01.procurementg01.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "RequestMaterial")
public class RequestMaterial {

	@Id
	private String requestId;
	private String requestedPerson;
	private String siteId;
	private String requestingDate;
	private String requestedDate;
	private List<Item> items;
	private String isImmediated;
	private Boolean isProcumentApproved;
	private Boolean isProcumentRejected;
	private String isSiteManagerApproved;
	private Boolean notifyManagement;
	private String siteManagerName;
	private String siteManagerID;
	@JsonIgnore
	private String isDeclinedBySiteManager;

	public String getRequestingDate() {
		return requestingDate;
	}

	public void setRequestingDate(String requestingDate) {
		this.requestingDate = requestingDate;
	}

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

	public String getSiteManagerName() {
		return siteManagerName;
	}

	public void setSiteManagerName(String siteManagerName) {
		this.siteManagerName = siteManagerName;
	}

	public Boolean getNotifyManagement() {
		return notifyManagement;
	}

	public void setNotifyManagement(Boolean notifyManagement) {
		this.notifyManagement = notifyManagement;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
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

	public Boolean getIsProcumentRejected() {
		return isProcumentRejected;
	}

	public void setIsProcumentRejected(Boolean isProcumentRejected) {
		this.isProcumentRejected = isProcumentRejected;
	}

}
