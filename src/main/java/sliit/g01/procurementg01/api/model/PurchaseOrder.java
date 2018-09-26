package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;

import java.util.*;

/**
 * @author anushka
 *
 * This is the final phase of the RequestMaterial.
 * Once a request for material has been approved, it becomes a,
 * purchase order which will be forwarded to the supplier(s).
 *
 */
public class PurchaseOrder {

    @Id
    private String orderId;
    private String requestId;   // to identify which material request resulted in this purchase order.
    private String sequentialReference;     // someone please tell me what this is. :(
    private List<Item> items;   // <supplier id, list of items>
    private String supplierId;  // supplier who offers the above list of items.
    private String orderStatus;
    private Date orderDate;
    private boolean draftPurchaseOrder;
    private boolean onHold;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSequentialReference() {
        return sequentialReference;
    }

    public void setSequentialReference(String sequentialReference) {
        this.sequentialReference = sequentialReference;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isDraftPurchaseOrder() {
        return draftPurchaseOrder;
    }

    public void setDraftPurchaseOrder(boolean draftPurchaseOrder) {
        this.draftPurchaseOrder = draftPurchaseOrder;
    }

    public boolean isOnHold() {
        return onHold;
    }

    public void setOnHold(boolean onHold) {
        this.onHold = onHold;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
