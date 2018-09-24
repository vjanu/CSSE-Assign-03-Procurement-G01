package sliit.g01.procurementg01.api.controller;

import java.util.List;

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

/**
 * @author Tharindu
 **/
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping("/item/add-new-item")
	public ResponseEntity<String> addCategory(@RequestBody Item item) {
		if (itemService.addItem(item)) {
			return new ResponseEntity<>("New Item Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item Not Created", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping("/item/")
	public List<Item> getAllCategories() {
		return itemService.getAllItems();
	}

	@GetMapping("/item/{itemId}")
	public Item getCategory(@PathVariable String itemId) {
		return itemService.getItem(itemId);
	}
}
