package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.RequestMaterial;

import sliit.g01.procurementg01.api.service.RequestMaterialService;


@RestController
@RequestMapping("/requestmaterial")
public class RequestMaterialController {
	@Autowired
	private RequestMaterialService requestmaterial;

	@PostMapping("/add-new-order")
	public ResponseEntity<String> addOrder(@RequestBody RequestMaterial requestMaterial) {
		if (requestmaterial.addOrder(requestMaterial)) {
			return new ResponseEntity<>("New order Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not Created", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping("/")
	public List<RequestMaterial> getAllOrders() {
		return requestmaterial.getAllOrders();
	}

	@GetMapping("/{orderId}")
	public RequestMaterial getOrder(@PathVariable String orderId) {
		return requestmaterial.getOrder(orderId);
	}
}
