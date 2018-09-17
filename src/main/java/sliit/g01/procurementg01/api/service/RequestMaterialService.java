package sliit.g01.procurementg01.api.service;

import java.util.List;


import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.model.Site;

public interface RequestMaterialService {

	
	Boolean addOrder(RequestMaterial requestmaterial);

	List<RequestMaterial> getAllOrders();

	RequestMaterial getOrder(String orderId);

	Boolean deleteOrder(String orderId);

	RequestMaterial updateRequest(RequestMaterial requestMaterial);
	
}
