package sliit.g01.procurementg01.api.service;


import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Policy;

import java.util.List;

/**
 * @author anushka
 */

@Service("PolicyService")
public interface PolicyService {

    // as specified in class diagram.
    Policy addPolicy(Policy policy);

    // as specified in the class diagram.
    Policy viewPolicy(String policyId);

    // this will return anything that contains the keyword we provide;
    // thus we don't necessarily have to provide an exact description when searching.
    List<Policy> getPoliciesWithDescription(String keyword);

    // for listing out all the company policies.
    List<Policy> getAllPolicies();
}
