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
		requestmaterialRepository.delete(requestmaterialRepository.findByOrderId(orderId));
		return true;
	}

	@Override
	public RequestMaterial updateRequest(String orderId, RequestMaterial requestMaterial) {
		RequestMaterial req = requestmaterialRepository.findByOrderId(orderId);

		if (requestMaterial.getIsProcumentApproved() != null)
			req.setIsProcumentApproved(requestMaterial.getIsProcumentApproved());
		if (requestMaterial.getItems() == null)
			requestMaterial.setItems(req.getItems());

		return requestmaterialRepository.save(requestMaterial);
	}

	@Override
	public List<RequestMaterial> getRequestsByStatus(String isSiteManagerApproved) {
		return requestmaterialRepository.findByIsSiteManagerApproved(isSiteManagerApproved);
	}

	@Override
	public List<RequestMaterial> getRequestsByImmediated(String isImmediated) {
		return requestmaterialRepository.findByIsImmediated(isImmediated);
	}

	@Override
	public List<RequestMaterial> getSiteMnagerApprovedRequests() {
		return requestmaterialRepository.findByisSiteManagerApproved("1");
	}

}
