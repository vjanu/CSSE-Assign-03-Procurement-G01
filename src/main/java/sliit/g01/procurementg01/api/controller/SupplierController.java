package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Supplier;
import sliit.g01.procurementg01.api.service.SupplierService;

@RestController
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping("/supplier/addNewSupplier")
	public ResponseEntity<String> addCategory(@RequestBody Supplier supplier) {
		if (supplierService.addSupplier(supplier)) {
			return new ResponseEntity<>("New Supplier Added!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Couldn't add supplier!", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping("/supplier/")
	public List<Supplier> getAllCategories() {
		return supplierService.getAllSuppliers();
	}

	@GetMapping("/supplier/{supplierId}")
	public Supplier getCategory(@PathVariable String supplierId) {
		return supplierService.getSupplier(supplierId);
	}

	@DeleteMapping("supplier/{supplierId}")
	public void deleteCategory(@PathVariable String supplierId) {
		supplierService.deleteSupplier(supplierId);
		
	}

	
	
}