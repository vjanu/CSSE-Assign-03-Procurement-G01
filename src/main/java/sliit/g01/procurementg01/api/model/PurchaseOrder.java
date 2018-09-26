package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * @author anushka
 *
 * This is the final phase of the RequestMaterial.
 * Once a request for material has been approved, it becomes a,
 * purchase order which will be forwarded to the supplier(s).
 *
 */
@Document(collection = "PurchaseOrder")
public class PurchaseOrder {

    @Id
    private String orderId;
    private String requestId;   // to identify which material request resulted in this purchase order.
    private String sequentialReference;     // someone please tell me what this is. :(
    private Map<String, String> items;   // <supplier id, list of items>
    private String supplierId;  // supplier who offers the above list of items.
    private String orderStatus;
    private Date orderDate;

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    private Date returnedDate; //modify by site manager
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

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
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
