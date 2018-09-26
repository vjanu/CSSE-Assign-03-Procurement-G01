package sliit.g01.procurementg01.api.service;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.model.RequestMaterial;

import java.util.List;
import java.util.Map;

/**
 * @author anushka
 */
public interface PurchaseOrderService {

    // change the quantity of item in an order.
    public boolean specifyQuantity(String itemId, int quantity);

    public PurchaseOrder getPurchaseOrder(String orderId);

    public List<PurchaseOrder> createOrder(RequestMaterial requestMaterial);

    public List<PurchaseOrder> getOrdersUnderMaterialRequest(String requestId); // request id = order id of material request.

    public String requestApproval();

    public void addPurchaseOrder(PurchaseOrder order);

    public void addPurchaseOrders(List<PurchaseOrder> orders);  // same as above, but for multiple items.
}
