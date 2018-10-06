package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Supplier;

/**
 * @author tharushi
 * @Modified Tharidnu
 */
public interface SupplierService {

	boolean addSupplier(Supplier supplier);

	List<Supplier> getAllSuppliers();

	Supplier getSupplier(String supplierId);

	Boolean updateSupplier(String employeeId, Supplier supplier);

	Supplier updateSupplier(Supplier supplier);

	Boolean deleteSupplier(String supplierId);

	Boolean supplierExists(String supplierId);

}
