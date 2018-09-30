package sliit.g01.procurementg01.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.service.impl.PurchaseOrderServiceImpl;

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

	@GetMapping("/order/onhold/{onHold}")
	public List<PurchaseOrder> getOnHoldPurchaseOrders(@PathVariable String onHold) {
		return purchaseOrderService.getOnHoldPurchaseOrders(onHold);
	}

	// retrieve the purchase orders
	@GetMapping("/order/all")
	public List<PurchaseOrder> getAllPurchaseOrder() {
		return purchaseOrderService.getAllPurchaseOrders();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/order/{orderId}")
	public PurchaseOrder update(@PathVariable String orderId, @RequestBody PurchaseOrder purchaseOrder) {
		return purchaseOrderService.updatePurchaseOrder(orderId, purchaseOrder);
	}

	// retrieve all purchase orders made for a single material request.
	// request should include the query parameter "requestId".
	// possible url:
	// <hostname>:<port>/order/for-request?requestId=<requestId>
	@GetMapping("order/for-request")
	public List<PurchaseOrder> getOrdersForMaterialRequest(@RequestBody Map<String, String> query) {
		List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

		if (query.containsKey("requestId")) {
			purchaseOrderList = purchaseOrderService.getOrdersUnderMaterialRequest(query.get("requestId"));
		}

		return purchaseOrderList;
	}

	// get orders by specific supplier and/or under specific status.
	// ex: pending orders of all suppliers, pending orders of supplier SUP001,
	// all pending orders, all orders.
	// possible urls:
	// <hostname>:<port>/order?supplier=<supplierId>&status=<orderStatus>
	public Map<String, List<PurchaseOrder>> getOrdersWithCriteria(@RequestBody Map<String, String> query) {
		Map<String, List<PurchaseOrder>> ordersForSuppliers = new HashMap<>();

		// possible scenarions:-
		// 1) both supplier and status are specified.(only one supplier and its
		// list of orders)
		// 2) neither of them are specified.(multiple suppliers with a list of
		// orders for each of them)
		// 3) only supplier is mentioned.(only one supplier and its list of
		// orders)
		// 4) only status is mentioned.(multiple suppliers with a list of
		// orders)
		// this is going to be long, sorry!

		// 1)
		if (query.containsKey("supplier") && query.containsKey("status")) {
			List<PurchaseOrder> orders = purchaseOrderService
					.getOrdersOfSpecificSupplierUnderSpecificStatus(query.get("supplier"), query.get("status"));
			ordersForSuppliers.put(query.get("supplier"), orders);
		}
		// 3)
		else if (query.containsKey("supplier") && !query.containsKey("status")) {
			List<PurchaseOrder> orders = purchaseOrderService.getOrdersofSpecificSupplier(query.get("supplier"));
			ordersForSuppliers.put(query.get("supplier"), orders);
		}
		// 4)
		else if (!query.containsKey("supplier") && query.containsKey("status")) {
			// here we get the response frmo the service layer already tucked
			// inside a map,yey!
			ordersForSuppliers = purchaseOrderService.getOrdersUnderSpecificStatus(query.get("status"));
		}
		// 2)
		else {
			// basically get all the orders, but grouped by the supplier.
			ordersForSuppliers = purchaseOrderService.getAllOrders();
		}

		return ordersForSuppliers;
	}

}
