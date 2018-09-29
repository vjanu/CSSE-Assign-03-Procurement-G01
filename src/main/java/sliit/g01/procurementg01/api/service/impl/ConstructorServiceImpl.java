package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Constructor;
import sliit.g01.procurementg01.api.repository.ConstructorRepository;
import sliit.g01.procurementg01.api.service.ConstructorService;

@Service("ConstructorService")
public class ConstructorServiceImpl implements ConstructorService {

	@Autowired
	private ConstructorRepository constructorRepository;

	@Override
	public Constructor addConstructor(Constructor constructor) {

		return constructorRepository.save(constructor);
	}

	@Override
	public List<Constructor> getAllConstructors() {
		return constructorRepository.findAll();
	}

	@Override
	public Constructor updateConstructor(String employeeId, Constructor constructor) {
		Constructor cs = constructorRepository.findByEmployeeId(employeeId);

		cs.setIsBanned(constructor.getIsBanned());

		return constructorRepository.save(cs);
	}

}
