package sliit.g01.procurementg01.api.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.service.RequestMaterialService;

/**
 * @author Tharindu TCJ
 **/
@RestController
@RequestMapping("/requestmaterial")
public class RequestMaterialController {

	@Autowired
	private RequestMaterialService requestMaterialService;

	// add  new request by constructor to the system.
//in this when sending data,constructor add request by using an unique requestId
//with its own other attributes relevant to the request
//addRequest  method will helps to add all the details of the assigned request
//and passed to the database.
	@RequestMapping(value = "/add-new-request", method = RequestMethod.POST)
	public ResponseEntity<String> addRequest(@RequestBody RequestMaterial requestMaterial) {
		requestMaterial.setRequestId("MR" + RandomStringUtils.randomNumeric(5));
		if (requestMaterialService.addOrder(requestMaterial)) {
			return new ResponseEntity<>("New request Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not Created", HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@GetMapping("/")
	// get all requests which were assigned by the constructor.
	public List<RequestMaterial> getAllRequests() {
		return requestMaterialService.getAllrequests();
	}

	@GetMapping("/sitemanager-approved/")
	public List<RequestMaterial> getSiteMnagerApprovedRequests() {
		return requestMaterialService.getSiteMnagerApprovedRequests();
	}

	@GetMapping("/{requestId}")
	public RequestMaterial getOrder(@PathVariable String requestId) {
		return requestMaterialService.getRequest(requestId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{requestId}")
	public Boolean update(@PathVariable String requestId, @RequestBody RequestMaterial requestMaterial) {
		return requestMaterialService.updateRequest(requestId, requestMaterial);
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	@PutMapping("/approve/procurement-staff")
	public String setProcumentStaffApproved(@RequestParam Map<String, String> query) {

		boolean status = false;

		if (query.containsKey("requestId") && query.containsKey("isProcumentApproved")) {
			String requestId = query.get("requestId");
			Boolean isProcumentApproved = Boolean.valueOf(query.get("isProcumentApproved"));
			status = requestMaterialService.setProcumentStaffApproved(requestId, isProcumentApproved);
		}

		return "Success:" + Boolean.toString(status);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/remove/{requestId}")
	public Boolean remove(@PathVariable String requestId) {
		return requestMaterialService.deleteRequest(requestId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/request/{isSiteManagerApproved}")
	public List<RequestMaterial> getRequestByStatus(@PathVariable String isSiteManagerApproved) {
		return requestMaterialService.getRequestsByStatus(isSiteManagerApproved);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/request/immediate/{isImmediated}")
	public List<RequestMaterial> getRequestByImmediation(@PathVariable String isImmediated) {
		return requestMaterialService.getRequestsByImmediated(isImmediated);
	}

}
