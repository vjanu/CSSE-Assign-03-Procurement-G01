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

    public List<PurchaseOrder> getAllPurchaseOrders();

    public List<PurchaseOrder> getOnHoldPurchaseOrders(String onHold);

    public List<PurchaseOrder> getOrdersUnderMaterialRequest(String requestId); // request id = order id of material request.

    // things like pending orders of a specific supplier and stuff.
    public List<PurchaseOrder> getOrdersOfSpecificSupplierUnderSpecificStatus(String supplierId, String orderStatus);

    // get all pending orders or something similar to that, grouped by supplier.
    public Map<String, List<PurchaseOrder>> getOrdersUnderSpecificStatus(String orderStatus);

    // get all orders of all suppliers, grouped by the supplier.
    public Map<String, List<PurchaseOrder>> getAllOrders();

    // get all orders issued for a specific supplier.
    public List<PurchaseOrder> getOrdersofSpecificSupplier(String supplierId);

    public String requestApproval();

    public void addPurchaseOrder(PurchaseOrder order);

    public void addPurchaseOrders(List<PurchaseOrder> orders);  // same as above, but for multiple items.

    PurchaseOrder updatePurchaseOrder(String orderId, PurchaseOrder purchaseOrder);
}
