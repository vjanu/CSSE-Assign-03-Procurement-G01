package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import sliit.g01.procurementg01.api.model.Login;

public interface LoginRepository extends MongoRepository<Login, String>{
	Login save(String employeeId);
}
