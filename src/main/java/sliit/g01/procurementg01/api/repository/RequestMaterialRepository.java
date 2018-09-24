package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.RequestMaterial;

import java.util.List;

public interface RequestMaterialRepository extends MongoRepository<RequestMaterial, String> {
	RequestMaterial findByOrderId(String orderId);
	List<RequestMaterial> findByIsSiteManagerApproved(String isSiteManagerApproved);

	List<RequestMaterial> findByIsImmediated(String isImmediated);

	// RequestMaterial findOne(String orderId);

	// void updateRequest();
}
