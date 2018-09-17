package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Item")
public class Item {

	private String itemId;
	private String itemName;
	private String categoryId;
	private float price;
	private String deliveryInformation;
	private boolean isRestrictedItem;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDeliveryInformation() {
		return deliveryInformation;
	}

	public void setDeliveryInformation(String deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}

	public boolean isRestrictedItem() {
		return isRestrictedItem;
	}

	public void setRestrictedItem(boolean isRestrictedItem) {
		this.isRestrictedItem = isRestrictedItem;
	}
}
