package sliit.g01.procurementg01.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.service.ItemService;
import sliit.g01.procurementg01.api.service.impl.SupplierServiceImpl;

/**
 * @author Tharindu
 * @author Tharushi
 **/
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private SupplierServiceImpl supplierService;

	@PostMapping("/item/add-new-item")
	public ResponseEntity<String> addCategory(@RequestBody Item item) {
		item.setItemId(UUID.randomUUID().toString());
		if (itemService.addItem(item)) {
			return new ResponseEntity<>("New Item Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item Not Created", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	// this should be used when a supplier is adding an item to the database.
	@PostMapping("/item")
	public ResponseEntity<String> addItem(@RequestBody Item item) {
		// generate the item id on the fly.
		item.setItemId(UUID.randomUUID().toString());

		if (supplierService.supplierExists(item.getSupplierId())) {
			itemService.addItem(item);
			return new ResponseEntity<>("New Item Added", HttpStatus.OK);
		}

		return new ResponseEntity<>("Item Not Created; Check if supplier exists.", HttpStatus.NOT_IMPLEMENTED);
	}

	// get all items supplied by a specific supplier.
	@GetMapping("item/{supplierId}/items")
	public List<Item> getItemsSuppliedBy(@PathVariable String supplierId) {
		return itemService.getItemsSuppliedBy(supplierId);
	}

	// get all items offered by each supplier, grouped by the supplier.
	@GetMapping("/item")
	public Map<String, List<Item>> getAllCategories() {
		return itemService.getAllItemsGroupedBySupplier();
	}

	@GetMapping("/item/{itemId}")
	public Item getCategory(@PathVariable String itemId) {
		return itemService.getItem(itemId);
	}

}
