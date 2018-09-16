package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;

import java.util.List;

@RestController
@RequestMapping("/employee/site-manager")
public class SiteManagerController {

    @Autowired
    private SiteManagerRepository siteManagerRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String addSiteManager(@Validated @RequestBody SiteManager siteManager) {
        siteManagerRepository.save(siteManager);
        return "Added";
    }

  
    
    @GetMapping("/employee/site-manager/sites/{siteId}")
    public List<SiteManager> getManagersOfSite(@PathVariable String siteId) {
        return siteManagerRepository.findAllByManagedSiteId(siteId);
    }
}
