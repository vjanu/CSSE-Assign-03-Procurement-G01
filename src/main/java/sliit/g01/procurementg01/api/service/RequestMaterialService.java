package sliit.g01.procurementg01.api.service;

import java.util.List;


import sliit.g01.procurementg01.api.model.RequestMaterial;

public interface RequestMaterialService {

	
	Boolean addOrder(RequestMaterial requestmaterial);

	List<RequestMaterial> getAllOrders();

	RequestMaterial getOrder(String orderId);

	Boolean deleteOrder(String orderId);

	
}
