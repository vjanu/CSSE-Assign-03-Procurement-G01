package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.service.RequestMaterialService;

@RestController
@RequestMapping("/requestmaterial")
public class RequestMaterialController {
	@Autowired
	private RequestMaterialService requestMaterialService;

	@PostMapping("/add-new-order")
	public ResponseEntity<String> addOrder(@RequestBody RequestMaterial requestMaterial) {
		if (requestMaterialService.addOrder(requestMaterial)) {
			return new ResponseEntity<>("New order Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not Created", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping("/")
	public List<RequestMaterial> getAllOrders() {
		return requestMaterialService.getAllOrders();
	}

	@GetMapping("/{orderId}")
	public RequestMaterial getOrder(@PathVariable String orderId) {
		return requestMaterialService.getOrder(orderId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public RequestMaterial update(@PathVariable String id, @RequestBody RequestMaterial requestMaterial) {
		return requestMaterialService.updateRequest(id, requestMaterial);
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




