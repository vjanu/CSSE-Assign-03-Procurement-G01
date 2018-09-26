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


    /**
     * the requestMaterial object contains a list of items and their quantities that we are,
     * going to order. but these items may not always be ordered from a single supplier.
     * in that case, we group items by each supplier to make it easy for us to forward the,
     * correct purchase order to the supplier.
     *
     * @param requestMaterial: basically the request made by an employee, requesting items.
     * @return: a map where items relevant to each supplier is listed under the supplier id.
     */
    private Map<String, List<Item>> groupItemaBySupplier(RequestMaterial requestMaterial) {
        Map<String, List<Item>> itemsOrderedFromEachSupplier = new HashMap<>(); // <supplier id, list of items>
        Map<String, String> itemCodeAndQuantity = requestMaterial.getItems();

        for (String itemCode: itemCodeAndQuantity.keySet()) {
            int quantity = Integer.parseInt(itemCodeAndQuantity.get(itemCode));

            Item item = new Item();
            item.setItemId(itemCode);

            // now we are grouping.
        }

        // TODO: group items in reuqestMaterial by supplier. Preserve the quantity as well!

        return itemsOrderedFromEachSupplier;
    }


    /**
     * since we may get a list of items provided by multiple suppliers,
     * we need to make a purchase order for each supplier.
     *
     * this method will do just that.
     *
     * @param requestMaterial: the request made by the employee for materials. this is the base for our order.
     * @return: a list of orders where each order is made for a specific supplier.
     */
    public List<PurchaseOrder> createOrders(RequestMaterial requestMaterial) {
        List<PurchaseOrder> orders = new ArrayList<>();

        // TODO: create the orders.

        return orders;
    }


    /**
     * this will go through our list of items and change the quantity,
     * of the item.
     *
     * @param itemId: id of the item in question.
     * @param newQuantity: new quantity we are ordering.
     */
    public void changeQuantity(String itemId, int newQuantity) {

    }
}
