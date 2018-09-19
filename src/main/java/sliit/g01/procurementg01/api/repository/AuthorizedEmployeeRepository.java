package sliit.g01.procurementg01.api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.AuthorizedEmployee;

import java.util.List;

public interface AuthorizedEmployeeRepository extends MongoRepository<AuthorizedEmployee, String> {

    AuthorizedEmployee findByEmployeeId(String employeeId);
    List<AuthorizedEmployee> findAllByEmployeeType(String employeeType);
}
