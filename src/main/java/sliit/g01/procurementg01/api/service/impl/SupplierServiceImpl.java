package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Supplier;
import sliit.g01.procurementg01.api.repository.SupplierRepository;
import sliit.g01.procurementg01.api.service.SupplierService;

/**
 * @author tharushi
 */
@Service("SupplierService")
public class SupplierServiceImpl implements SupplierService {

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
		// get the existing supplier object and ask mongo to delete it for us.
		Supplier existingSupplier = supplierRepository.findBySupplierId(supplierId);

		if (existingSupplier != null) {
			supplierRepository.delete(existingSupplier);
			return true;
		}

		return false;
	}

	@Override
	public Boolean supplierExists(String supplierId) {
		return supplierRepository.existsSupplierBySupplierId(supplierId);
	}

	@Override
	public Boolean updateSupplier(String employeeId, Supplier supplier) {
		Supplier sup = supplierRepository.findBySupplierId(employeeId);

		sup.setIsBanned(supplier.getisBanned());

		return (supplierRepository.save(sup) != null);
	}

	@Override
	public Supplier updateSupplier(Supplier supplier) {
		// check if a supplier under the id already exists.
		Supplier existingSupplier = supplierRepository.findBySupplierId(supplier.getSupplierId());

		if (existingSupplier != null) {
			return supplierRepository.save(supplier); // existing entry will be
														// replaced since the
														// ids are the same.
		}

		return new Supplier();
	}

	public Supplier getSupplier(String email, String phone) {
		return supplierRepository.findByEmailAndPhone(email, phone);
	}
}
