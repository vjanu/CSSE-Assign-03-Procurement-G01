package sliit.g01.procurementg01.api.service;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.RequestMaterial;

import java.util.List;
import java.util.Map;

/**
 * @author anushka
 */
public interface PurchaseOrderService {

    // change the quantity of item in an order.
    public boolean specifyQuantity(String itemId, int quantity);

    public Map<String, List<Item>> createOrder(RequestMaterial requestMaterial);

    public String requestApproval();
}
