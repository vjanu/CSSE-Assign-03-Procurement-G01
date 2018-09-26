package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.service.impl.PurchaseOrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author anushka
 */
@RestController
public class PurchaseOrderController {


    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;


    // add new purchase order or orders depending on number of suppliers.
    @PostMapping("/order")
    public ResponseEntity<String> addPurcahseOrders(@RequestBody RequestMaterial requestMaterial) {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderService.createOrder(requestMaterial);
        purchaseOrderService.addPurchaseOrders(purchaseOrderList);

        return new ResponseEntity<>("Order(s) added.", HttpStatus.OK);

    }

    // retrieve the purchase order under the order id..
    @GetMapping("/order/{orderId}")
    public PurchaseOrder getPurchaseOrder(@PathVariable String orderId) {
        return purchaseOrderService.getPurchaseOrder(orderId);
    }

    // retrieve all purchase orders made for a single material request.
    // request should include the query parameter "requestId".
    // possible url:
    // <hostname>:<port>/order/for-request?requestId=<requestId>
    @GetMapping("order/for-request")
    public List<PurchaseOrder> getOrdersForMaterialRequest(@RequestBody Map<String, String> query) {
        List<PurchaseOrder> purchaseOrderList =  new ArrayList<>();

        if (query.containsKey("requestId")) {
            purchaseOrderList = purchaseOrderService.getOrdersUnderMaterialRequest(query.get("requestId"));
        }

        return purchaseOrderList;
    }
}
