package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.Supplier;

public interface SupplierRepository extends MongoRepository <Supplier, String> {
	Supplier findBySupplierId (String supplierId);
}
