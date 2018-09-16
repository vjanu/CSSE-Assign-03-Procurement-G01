package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RequestMaterial")
public class RequestMaterial {
@Id
    private String orderId;
	private String[] item;
	private double quantity;
	public String[] getItem() {
		return item;
	}
	public void setItem(String[] item) {
		this.item = item;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
