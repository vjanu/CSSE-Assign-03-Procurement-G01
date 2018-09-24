package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.repository.RequestMaterialRepository;
import sliit.g01.procurementg01.api.service.RequestMaterialService;;

@Service("requestmaterialService")
public class RequestMaterialServiceImpl implements RequestMaterialService {

	@Autowired
	private RequestMaterialRepository requestmaterialRepository;

	@Override
	public Boolean addOrder(RequestMaterial requestmaterial) {
		return (requestmaterialRepository.save(requestmaterial) != null);

	}

	@Override
	public List<RequestMaterial> getAllOrders() {
		return requestmaterialRepository.findAll();

	}

	@Override
	public RequestMaterial getOrder(String orderId) {
		return requestmaterialRepository.findByOrderId(orderId);
	}

	@Override
	public Boolean deleteOrder(String orderId) {
		return null;
	}

	@Override
	public RequestMaterial updateRequest(String orderId, RequestMaterial requestMaterial) {
		RequestMaterial req = requestmaterialRepository.findByOrderId(orderId);

		if (requestMaterial.getIsProcumentApproved() != null)
			req.setIsProcumentApproved(requestMaterial.getIsProcumentApproved());

		return requestmaterialRepository.save(req);
	}

	

}
