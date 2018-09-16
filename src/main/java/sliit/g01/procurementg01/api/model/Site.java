package sliit.g01.procurementg01.api.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * created by viraj
 **/
@Document(collection = "Site")
public class Site  {

	@Id
    private int siteId;
	private String siteName;
	private String address;
	private Map items;
	private float storageCapacity;
	private float currentCapacity;

    public Site() {}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
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
	public Map getItems() {
		return items;
	}
	public void setItems(Map items) {
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




	


   
}
