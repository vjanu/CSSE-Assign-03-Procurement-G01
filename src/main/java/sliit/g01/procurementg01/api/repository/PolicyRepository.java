package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.Policy;

import java.util.List;

/**
 * @author anushka
 */
public interface PolicyRepository extends MongoRepository<Policy, String> {

    Policy findByPolicyId(String policyId);

    List<Policy> findAllByPolicyIdExists();

    List<Policy> findAllByDescriptionContains(String keyword);

    void deletePolicyByPolicyId(String policyId);
}
