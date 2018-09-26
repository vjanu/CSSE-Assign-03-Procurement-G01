package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.service.impl.PurchaseOrderServiceImpl;

/**
 * @author anushka
 */
@RestController
public class PurchaseOrderController {


    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;

    // retrieve the purchase order under the order id..
    @GetMapping("/order/{orderId}")
    public PurchaseOrder getPurchaseOrder(@PathVariable String orderId) {
        return purchaseOrderService.getPurchaseOrder(orderId);
    }
}
