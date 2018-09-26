package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Constructor;
import sliit.g01.procurementg01.api.service.impl.ConstructorServiceImpl;

@RestController
public class ConstructorController {
	  @Autowired
	    private ConstructorServiceImpl constructorService;

	    @PostMapping("/employee/constructor")
		public Constructor addConstructor(@Validated @RequestBody Constructor constructor) {
		    return constructorService.addConstructor(constructor);
		}
	    	 
	    @GetMapping("/employee/constructor/")
		public List<Constructor> getAllConstructors() {
			return constructorService.getAllConstructors();
		}
	
}






