package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sliit.g01.procurementg01.api.model.AuthorizedEmployee;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.AuthorizedEmployeeRepository;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;

import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private SiteManagerRepository siteManagerRepository;

    @Autowired
    private AuthorizedEmployeeRepository authorizedEmployeeRepository;

    /**
     * Add an employee
     **/
    @PostMapping("employee/add")
    public String addEmployee(@RequestParam("category") String category, @RequestBody Map<String, String> payload) {
        switch (category.toLowerCase()) {
            case "sitemanager":
                siteManagerRepository.save(siteManager);
                break;

            case "authorizedemployee":
                authorizedEmployeeRepository.save(authorizedEmployee);
                break;

            default:
                authorizedEmployeeRepository.save(authorizedEmployee);
        }

        return "Executed";
    }
}
