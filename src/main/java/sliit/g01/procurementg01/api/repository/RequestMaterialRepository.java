package sliit.g01.procurementg01.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.RequestMaterial;

public interface RequestMaterialRepository extends MongoRepository<RequestMaterial, String> {
	RequestMaterial findByOrderId(String orderId);

	List<RequestMaterial> findByisSiteManagerApproved(String isSiteManagerApproved);

	// RequestMaterial findOne(String orderId);

	// void updateRequest();
}
