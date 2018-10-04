package sliit.g01.procurementg01.api.service;

import java.util.List;


import sliit.g01.procurementg01.api.model.RequestMaterial;

public interface RequestMaterialService {

	Boolean addOrder(RequestMaterial requestmaterial);


	List<RequestMaterial> getAllrequests();

	List<RequestMaterial> getSiteMnagerApprovedRequests();

	RequestMaterial getRequest(String requestId);

	Boolean deleteRequest(String requestId);

	RequestMaterial updateRequest(String requestId, RequestMaterial requestMaterial);

	Boolean setProcumentStaffApproved(String requestId, Boolean isProcumentApproved);

	List<RequestMaterial> getRequestsByStatus(String isSiteManagerApproved);

	List<RequestMaterial> getRequestsByImmediated(String isImmediated);

	boolean addItem(RequestMaterial requestMaterial);

}
