package sliit.g01.procurementg01.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.AccountingStaff;
/**
 * @author Viraj
 */

public interface AccountingStaffRepository extends MongoRepository<AccountingStaff, String> {

	AccountingStaff findByEmployeeId(String employeeId);
    List<AccountingStaff> findByDeleted(boolean deleted); 
    List<AccountingStaff> findByCategoryAndDeleted(String status, boolean deleted);

}
