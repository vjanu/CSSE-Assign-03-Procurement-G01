package sliit.g01.procurementg01.api.service;

import java.util.List;
import sliit.g01.procurementg01.api.model.Supplier;

public interface SupplierService {
	boolean addSupplier( Supplier supplier);
	
	List<Supplier> getAllSuppliers();
	
	Supplier getSupplier(String supplierId);
	
	Boolean deleteSupplier(String supplierId);
	
}
