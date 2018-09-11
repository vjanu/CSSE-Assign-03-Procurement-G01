package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;

import java.util.List;

@RestController
public class SiteManagerController {

    @Autowired
    private SiteManagerRepository siteManagerRepository;

    @PostMapping("/employee/site-manager")
    public String addSiteManager(@RequestBody SiteManager siteManager) {
        siteManagerRepository.save(siteManager);

        return "Executed";
    }

    @GetMapping("/employee/site-manager/sites/{siteId}")
    public List<SiteManager> getManagersOfSite(@PathVariable String siteId) {
        return siteManagerRepository.findAllByManagedSiteId(siteId);
    }
}
