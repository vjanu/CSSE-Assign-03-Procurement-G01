package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Constructor;
import sliit.g01.procurementg01.api.service.ConstructorService;

@RestController
public class ConstructorController {
	@Autowired
	private ConstructorService constructorService;

	@PostMapping("/employee/constructor")
	public Constructor addConstructor(@Validated @RequestBody Constructor constructor) {
		constructor.setEmployeeId("CS" + RandomStringUtils.randomNumeric(5));
		return constructorService.addConstructor(constructor);
	}

	@GetMapping("/employee/constructor/")
	public List<Constructor> getAllConstructors() {
		return constructorService.getAllConstructors();
	}

	@PutMapping("/employee/update/{employeeId}")
	public Constructor update(@PathVariable String employeeId, @RequestBody Constructor constructor) {
		return constructorService.updateConstructor(employeeId, constructor);
	}

}
