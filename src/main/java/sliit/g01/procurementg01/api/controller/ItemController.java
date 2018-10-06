package sliit.g01.procurementg01.api.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<String> addItem(@RequestBody Item item) {
		item.setItemId("IT" + RandomStringUtils.randomNumeric(5));
		if (itemService.addItem(item)) {
			return new ResponseEntity<>("New Item Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item Not Created", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	// update an item.
    @PutMapping("/item")
    public ResponseEntity<String> updateItem(@RequestBody Item item) {
        if (itemService.updateItem(item)) {
            return new ResponseEntity<>("New Item Added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item Not Created", HttpStatus.NOT_IMPLEMENTED);
        }

    }

	// get all items supplied by a specific supplier.
	@GetMapping("item/{supplierId}/items")
	public List<Item> getItemsSuppliedBy(@PathVariable String supplierId) {
		return itemService.getItemsSuppliedBy(supplierId);
	}

	// get all items offered by each supplier, grouped by the supplier.
	@GetMapping("/item")
	public Map<String, List<Item>> getAllItems() {
		return itemService.getAllItemsGroupedBySupplier();
	}

	// get details of a specific item.
	@GetMapping("/item/{itemId}")
	public Item getItem(@PathVariable String itemId) {
		return itemService.getItem(itemId);
	}

	// delete an item.
	@DeleteMapping("/item/{itemId}")
	public void deleteItem(@PathVariable String itemId) {
		itemService.deleteItem(itemId);
	}

}
