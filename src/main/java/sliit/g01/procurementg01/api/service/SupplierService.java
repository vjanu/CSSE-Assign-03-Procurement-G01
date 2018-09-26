package sliit.g01.procurementg01.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Supplier;

public interface SupplierService {
	boolean addSupplier( Supplier supplier);
	
	List<Supplier> getAllSuppliers();
	
	Supplier getSupplier(String supplierId);

	// will return the whole supplier object of the supplier whose offering the given item.
	Supplier getSupplierWhoOffersItem(String itemId);

	Boolean deleteSupplier(String supplierId);


	
}
