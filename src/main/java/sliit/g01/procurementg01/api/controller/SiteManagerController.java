package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;
import sliit.g01.procurementg01.api.service.impl.SiteManagerServiceImpl;

@RestController
public class SiteManagerController {


    @Autowired
    private SiteManagerServiceImpl siteManagerService;

    @PostMapping("/employee/site-manager")
	public SiteManager addSiteManager(@Validated @RequestBody SiteManager siteManager) {
	    return siteManagerService.addSiteManager(siteManager);
	}

	@GetMapping("/employee/site-manager/sites/{siteId}")
	public SiteManager getSiteManagerOfSite(@PathVariable String siteId) {
		return siteManagerService.getSiteManagerOfSite(siteId);
	}

	@GetMapping("/employee/site-manager/{managerId}/site")
    public String getManagedSiteId(@PathVariable String managerId) {
	    return siteManagerService.getManagedSite(managerId);
    }
}
