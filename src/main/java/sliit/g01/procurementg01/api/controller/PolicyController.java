package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sliit.g01.procurementg01.api.model.Policy;
import sliit.g01.procurementg01.api.service.PolicyService;
import sliit.g01.procurementg01.api.service.impl.PolicyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author anushka
 */

@RestController
public class PolicyController {

    @Autowired
    private PolicyServiceImpl policyService;

    // add new policy.
    @PostMapping("/policy")
    public Policy addPolicy(@RequestBody @Validated Policy policy) {
        System.out.println(policy.getDescription());
        return policyService.addPolicy(policy);
    }

    // view all company policies.
    @GetMapping("/policy/all")
    public List<Policy> viewAllPolicies() {
        return policyService.getAllPolicies();
    }

    // get a specific policy by id.
    @GetMapping("/policy/{policyId}")
    public Policy viewPolicy(@PathVariable String policyId) {
        return policyService.viewPolicy(policyId);
    }

    // get policy(s) that has the provided keyword in the description.
    // this is going to have a query parameter.
    // possible url: <hostname>:<port>/policy?description=<keyword>
    @GetMapping("/policy")
    public List<Policy> viewPoliciesWithDescription(@RequestParam Map<String, String> query) {
        if (query.containsKey("description")) {
            String keyword = query.get("description");
            return policyService.getPoliciesWithDescription(keyword);
        }

        return new ArrayList<Policy>();
    }

    // delete a policy.
    @DeleteMapping("policy/{policyId}")
    public boolean deletePolicy(@PathVariable String policyId) {
        return policyService.deletePolicy(policyId);
    }
}
