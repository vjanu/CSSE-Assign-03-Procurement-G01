package sliit.g01.procurementg01.api.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.Constructor;


public interface ConstructorRepository extends MongoRepository<Constructor, String> {

	Constructor findByEmployeeId(String employeeId);
}
