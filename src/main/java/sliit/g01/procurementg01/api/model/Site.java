package sliit.g01.procurementg01.api.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by viraj
 **/
@Document(collection = "Site")
public class Site {

    @Id
	private String siteId;
	private String siteName;
	private String address;
	private Map<String, String> items;
	private float storageCapacity;
	private float currentCapacity;
	private SiteManager siteManager;

	public Site() {
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	public float getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(float storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public float getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(float currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public SiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(SiteManager siteManager) {
		this.siteManager = siteManager;
	}
}
