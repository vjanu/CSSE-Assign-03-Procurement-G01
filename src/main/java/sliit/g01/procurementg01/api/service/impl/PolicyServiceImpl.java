package sliit.g01.procurementg01.api.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Policy;
import sliit.g01.procurementg01.api.repository.PolicyRepository;
import sliit.g01.procurementg01.api.service.PolicyService;

import java.util.List;
import java.util.UUID;

/**
 * @author anushka
 */
@Service("PolicyService")
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public Policy addPolicy(Policy policy) {
        // give a random id
        policy.setPolicyId("PL" + RandomStringUtils.randomNumeric(5));
        return policyRepository.save(policy);
    }

    @Override
    public Policy viewPolicy(String policyId) {
        Policy policy = policyRepository.findByPolicyId(policyId);

        // if no policies exists in the db under the given policyId,
        // the variable will be null.
        if (policy == null) {
            policy = new Policy();
        }

        return policy;
    }

    @Override
    public List<Policy> getPoliciesWithDescription(String keyword) {
        // here, if no results are found, this will return an empty list,
        // rather than a null pointer so we don't have to worry about null values.
        return policyRepository.findAllByDescriptionContains(keyword);
    }

    @Override
    public List<Policy> getAllPolicies() {
        // here, if no results are found, this will return an empty list,
        // rather than a null pointer so we don't have to worry about null values.
        return policyRepository.findAll();
    }

    @Override
    public boolean deletePolicy(String policyId) {
        policyRepository.deletePolicyByPolicyId(policyId);

        // validate if it entry was deleted.
        // if the entry is deleted, policies will be null.
        Policy policy = viewPolicy(policyId);
        return policy.getPolicyId() == null;
    }
}
