package sliit.g01.procurementg01.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.repository.PurchaseOrderRepository;
import sliit.g01.procurementg01.api.service.PurchaseOrderService;

import java.util.*;

/**
 * @author anushka
 */
@Service("PurchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {


    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    @Override
    public boolean specifyQuantity(String itemId, int quantity) {
        return false;
    }

    /**
     * This method will produce a purchase order for each supplier.
     * (a material request may contain materials offered by different suppliers)
     *
     * @param requestMaterial:  an object that encloses a request for materials made by an employee.
     */
    @Override
    public List<PurchaseOrder> createOrder(RequestMaterial requestMaterial) {
        List<PurchaseOrder> orders = new ArrayList<>(); // holds the purchase orders(one order per supplier).
        Map<String, List<Item>> itemsOrderedFromEachSupplier = new HashMap<>(); // list of items mapped against the supplier.
        Map<String, String> itemIdAndQuantities = requestMaterial.getItems(); // quantity required, mapped against item id.

        // request material object has a map of items where quantity of each item is mapped against its item code.
        // we need to get full details of the item(from the database) and group the items by the supplier.
        for (String itemId: itemIdAndQuantities.keySet()) {
            String quantity = itemIdAndQuantities.get(itemId);
            Item i = itemService.getItem(itemId);

            if (i != null) {
                i.setQuantity(quantity);

                // now we group.
                if (itemsOrderedFromEachSupplier.containsKey(i.getSupplierId())) {
                    // get the existing item list of the supplier so that we won't replace it.
                    List<Item> existingItems = itemsOrderedFromEachSupplier.get(i.getSupplierId());
                    existingItems.add(i);

                    // put it back so that the list mapped against the supplier is up to date.
                    itemsOrderedFromEachSupplier.put(i.getSupplierId(), existingItems);
                }
                else {
                    // create a new list of items for future use.
                    List<Item> itemList = new ArrayList<>();
                    itemList.add(i);

                    // create a new entry in the map for this supplier.
                    itemsOrderedFromEachSupplier.put(i.getSupplierId(), itemList);
                }
            }
        }

        // now that we have grouped each item by its supplier, we can create a purchase order,
        // for each supplier.
        // we keep the requestId the same with varying order ids.
        for (String supplierId: itemsOrderedFromEachSupplier.keySet()) {
            PurchaseOrder p = new PurchaseOrder();

            p.setRequestId(requestMaterial.getOrderId());
            p.setOrderId(UUID.randomUUID().toString());
            p.setDraftPurchaseOrder(true);  // will be a draft as long as the payment isn't made.
            p.setItems(itemsOrderedFromEachSupplier.get(supplierId));
            p.setOnHold(true);  // will stay on hold until a staff member approves this.
            p.setOrderDate(new Date());
            p.setOrderStatus("Pending approval");
            p.setSequentialReference("No idea");
            p.setSupplierId(supplierId);

            orders.add(p);
        }

        return orders;
    }

    @Override
    public String requestApproval() {
        return null;
    }

    @Override
    public PurchaseOrder getPurchaseOrder(String orderId) {
        return purchaseOrderRepository.getPurchaseOrderByOrderId(orderId);
    }

    @Override
    public List<PurchaseOrder> getOrdersUnderMaterialRequest(String requestId) {
        return purchaseOrderRepository.getPurchaseOrdersByRequestId(requestId); // request id is the order id of a material request. confusing i know!
    }

    // save a single purchase order to the database.
    // for multiple orders, call this method iteratively; LOL!
    @Override
    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
    }

    // save multiple orders to database.
    @Override
    public void addPurchaseOrders(List<PurchaseOrder> orders) {
        for (PurchaseOrder purchaseOrder: orders) {
            addPurchaseOrder(purchaseOrder);
        }
    }
}
