package sliit.g01.procurementg01.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Supplier;

/**
 * @author tharushi
 */
public interface SupplierService {

	boolean addSupplier( Supplier supplier);

	List<Supplier> getAllSuppliers();

	Supplier getSupplier(String supplierId);

	Boolean deleteSupplier(String supplierId);

    Boolean supplierExists(String supplierId);

	
}
