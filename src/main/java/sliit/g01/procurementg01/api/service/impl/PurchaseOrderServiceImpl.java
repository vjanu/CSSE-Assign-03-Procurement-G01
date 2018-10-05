package sliit.g01.procurementg01.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.PurchaseOrder;
import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.model.Supplier;
import sliit.g01.procurementg01.api.repository.PurchaseOrderRepository;
import sliit.g01.procurementg01.api.service.PurchaseOrderService;

/**
 * @author anushka
 */
@Service("PurchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private SupplierServiceImpl supplierService;

	@Autowired
	private SiteServiceImpl siteService;

	@Override
	public boolean specifyQuantity(String itemId, int quantity) {
		return false;
	}

	/**
	 * This method will produce a purchase order for each supplier. (a material
	 * request may contain materials offered by different suppliers)
	 *
	 * @param requestMaterial:
	 *            an object that encloses a request for materials made by an
	 *            employee.
	 */
	@Override
	public List<PurchaseOrder> createOrder(RequestMaterial requestMaterial) {
		List<PurchaseOrder> orders = new ArrayList<>(); // holds the purchase
														// orders(one order per
														// supplier).
		Map<String, List<Item>> itemsOrderedFromEachSupplier = new HashMap<>(); // list
																				// of
																				// items
																				// mapped
																				// against
																				// the
																				// supplier.
		Map<String, String> itemIdAndQuantities = requestMaterial.getItems(); // quantity
																				// required,
																				// mapped
																				// against
																				// item
																				// id.
		Site deliverySite = siteService.getSite(requestMaterial.getSiteId()); // this
																				// is
																				// where
																				// the
																				// order
																				// is
																				// headed.

		// request material object has a map of items where quantity of each
		// item is mapped against its item code.
		// we need to get full details of the item(from the database) and group
		// the items by the supplier.
		for (String itemId : itemIdAndQuantities.keySet()) {
			String quantity = itemIdAndQuantities.get(itemId);
			Item i = itemService.getItem(itemId);

			if (i != null) {
				i.setQuantity(quantity);

				// now we group.
				if (itemsOrderedFromEachSupplier.containsKey(i.getSupplierId())) {
					// get the existing item list of the supplier so that we
					// won't replace it.
					List<Item> existingItems = itemsOrderedFromEachSupplier.get(i.getSupplierId());
					existingItems.add(i);

					// put it back so that the list mapped against the supplier
					// is up to date.
					itemsOrderedFromEachSupplier.put(i.getSupplierId(), existingItems);
				} else {
					// create a new list of items for future use.
					List<Item> itemList = new ArrayList<>();
					itemList.add(i);

					// create a new entry in the map for this supplier.
					itemsOrderedFromEachSupplier.put(i.getSupplierId(), itemList);
				}
			}
		}

		// now that we have grouped each item by its supplier, we can create a
		// purchase order,
		// for each supplier.
		// we keep the requestId the same with varying order ids.
		for (String supplierId : itemsOrderedFromEachSupplier.keySet()) {
			PurchaseOrder p = new PurchaseOrder();

			p.setRequestId(requestMaterial.getRequestId());
			p.setOrderId(requestMaterial.getRequestId() + "-" + supplierId);
			p.setDraftPurchaseOrder(true); // will be a draft as long as the
											// payment isn't made.
			p.setItems(itemsOrderedFromEachSupplier.get(supplierId));
			p.setOnHold("false");
			p.setOrderDate(new Date());
			// p.setOrderStatus("Pending approval");
			p.setSequentialReference("No idea");
			p.setSupplierId(supplierId);
			p.setDeliverySite(deliverySite);
			p.setOrderStatus("Pending");

			orders.add(p);
		}

		return orders;
	}

	@Override
	public PurchaseOrder getPurchaseOrder(String orderId) {
		return purchaseOrderRepository.getPurchaseOrderByOrderId(orderId);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return purchaseOrderRepository.findAll();
	}

	@Override
	public List<PurchaseOrder> getOnHoldPurchaseOrders(String onHold) {
		return purchaseOrderRepository.getPurchaseOrdersByOnHold(onHold);
	}

	@Override
	public List<PurchaseOrder> getOrdersUnderMaterialRequest(String requestId) {
		return purchaseOrderRepository.getPurchaseOrdersByRequestId(requestId);
	}

	@Override
	public String requestApproval() {
		return null;
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
		for (PurchaseOrder purchaseOrder : orders) {
			addPurchaseOrder(purchaseOrder);
		}
	}

	// for the given supplier, get the orders that are pending or something
	// similar to that.
	@Override
	public List<PurchaseOrder> getOrdersOfSpecificSupplierUnderSpecificStatus(String supplierId, String orderStatus) {
		return purchaseOrderRepository.getPurchaseOrdersBySupplierIdAndOrderStatus(supplierId, orderStatus);
	}

	// regardless of the supplier get all orders that are pending or something
	// similar to that.
	@Override
	public Map<String, List<PurchaseOrder>> getOrdersUnderSpecificStatus(String orderStatus) {
		Map<String, List<PurchaseOrder>> ordersOfSupplier = new HashMap<>();
		List<Supplier> suppliers = supplierService.getAllSuppliers();

		// now for each supplier we find orders that has the specified status.
		for (Supplier s : suppliers) {
			ordersOfSupplier.put(s.getSupplierId(), purchaseOrderRepository
					.getPurchaseOrdersBySupplierIdAndOrderStatus(s.getSupplierId(), orderStatus));
		}

		return ordersOfSupplier;
	}

	// for the given supplier, get the orders that are pending or something
	// similar to that.
	@Override
	public PurchaseOrder updatePurchaseOrder(String orderId, PurchaseOrder purchaseOrder) {
		PurchaseOrder order = purchaseOrderRepository.getPurchaseOrderByOrderId(orderId);
		if (purchaseOrder.getReturnedDate() != null)
			order.setReturnedDate(purchaseOrder.getReturnedDate());
		// if (purchaseOrder.getOrderStatus() != null)
		// order.setOrderStatus(purchaseOrder.getOrderStatus());
		if (purchaseOrder.isOnHold() != "false")
			order.setOnHold(purchaseOrder.isOnHold());
		return purchaseOrderRepository.save(order);
	}

	// regardless of the status, get all orders issued to a specific supplier.
	@Override
	public List<PurchaseOrder> getOrdersofSpecificSupplier(String supplierId) {
		return purchaseOrderRepository.getPurchaseOrdersBySupplierId(supplierId);
	}

	// regardless of the status, all orders of all suppliers are retrieved and
	// grouped by the supplier.
	@Override
	public Map<String, List<PurchaseOrder>> getAllOrders() {
		Map<String, List<PurchaseOrder>> ordersOfSupplier = new HashMap<>();
		List<Supplier> suppliers = supplierService.getAllSuppliers();

		for (Supplier s : suppliers) {
			ordersOfSupplier.put(s.getSupplierId(),
					purchaseOrderRepository.getPurchaseOrdersBySupplierId(s.getSupplierId()));
		}

		return ordersOfSupplier;
	}

	@Override
	public List<PurchaseOrder> createOrder(Site site) {
		// holds the purchase orders(one order per supplier).
		List<PurchaseOrder> orders = new ArrayList<>();
		// list of items mapped against the supplier.
		Map<String, List<Item>> itemsOrderedFromEachSupplier = new HashMap<>();
		// quantity required, mapped against item id.
		List<Item> siteItems = site.getItems();
		// this is where the order is headed.
		Site deliverySite = siteService.getSite(site.getSiteId());

		// request material object has a map of items where quantity of each
		// item is mapped against its item code.
		// we need to get full details of the item(from the database) and group
		// the items by the supplier.

		for (Item i : siteItems) {
			// now we group.
			if (itemsOrderedFromEachSupplier.containsKey(i.getSupplierId())) {
				// get the existing item list of the supplier so that we
				// won't replace it.
				List<Item> existingItems = itemsOrderedFromEachSupplier.get(i.getSupplierId());
				existingItems.add(i);

				// put it back so that the list mapped against the supplier
				// is up to date.
				itemsOrderedFromEachSupplier.put(i.getSupplierId(), existingItems);
			} else {
				// create a new list of items for future use.
				List<Item> itemList = new ArrayList<>();
				itemList.add(i);

				// create a new entry in the map for this supplier.
				itemsOrderedFromEachSupplier.put(i.getSupplierId(), itemList);
			}
		}

		// now that we have grouped each item by its supplier, we can create a
		// purchase order,
		// for each supplier.
		// we keep the requestId the same with varying order ids.
		for (String supplierId : itemsOrderedFromEachSupplier.keySet()) {
			PurchaseOrder p = new PurchaseOrder();

			p.setRequestId("RI" + RandomStringUtils.randomNumeric(5));
			p.setOrderId("OR" + RandomStringUtils.randomNumeric(5));
			p.setDraftPurchaseOrder(true); // will be a draft as long as the
											// payment isn't made.
			p.setItems(itemsOrderedFromEachSupplier.get(supplierId));
			p.setOnHold(false);
			p.setOrderDate(new Date());
			// p.setOrderStatus("Pending approval");
			p.setSequentialReference("No idea");
			p.setSupplierId(supplierId);
			p.setDeliverySite(deliverySite);
			p.setOrderStatus("Pending");

			orders.add(p);
		}

		return orders;
	}

}
