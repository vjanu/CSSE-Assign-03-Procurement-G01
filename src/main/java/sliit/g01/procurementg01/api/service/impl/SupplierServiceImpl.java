package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sliit.g01.procurementg01.api.model.Supplier;
import sliit.g01.procurementg01.api.repository.SupplierRepository;
import sliit.g01.procurementg01.api.service.SupplierService;

public class SupplierServiceImpl implements SupplierService{

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public boolean addSupplier(Supplier supplier) {
		return (supplierRepository.save(supplier) != null);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier getSupplier(String supplierId) {
		return supplierRepository.findBySupplierId(supplierId);
	}

	@Override
	public Boolean deleteSupplier(String supplierId) {
		return null;
	}

}
