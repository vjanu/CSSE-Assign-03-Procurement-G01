package sliit.g01.procurementg01.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sliit.g01.procurementg01.api.model.AccountingStaff;

/***
 * created by viraj
 ***/

public interface AccountingStaffRepository extends MongoRepository<AccountingStaff, String> {


}
