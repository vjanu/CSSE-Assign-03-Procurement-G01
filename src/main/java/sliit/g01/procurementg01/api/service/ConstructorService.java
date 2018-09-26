package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Constructor;

public interface ConstructorService {

	Constructor addConstructor(Constructor constructor);
	
	List<Constructor> getAllConstructors();
	
}
