package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tharindu
 **/
public class Item {

    @Id
	private String itemId;
	private String itemName;
	private String supplierId;
	private String categoryId;
	private float price;
	private String unitType;	// whether a unit is a packet or so.
	private String quantity;
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

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
