package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.service.AccountingStaffService;
import sliit.g01.procurementg01.api.service.SiteService;
/**
 * created by viraj
 **/
@RestController
@RequestMapping("/site")
public class SiteController {
	
	    @Autowired
	    private SiteService siteService;

	   @RequestMapping(method = RequestMethod.POST)
	    public String addSite(@Validated @RequestBody final Site site) {
		   siteService.addSite(site);
	        return "Added";
	    }
	   @RequestMapping(method = RequestMethod.GET)
	    public List<Site> listAllSites() {
	        return siteService.listAllSites();
	    }
	    
	   @RequestMapping(value = "/siteitem/{id}", method = RequestMethod.GET)
	    public List<Item> getAvailableItems(@PathVariable("id") final int id) {
	        return siteService.getAvailableItems(id);
	    }
	   
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	    public Site updateSite(@PathVariable("id") final int id, @Validated @RequestBody final Site site) {
	        return siteService.updateSite(id, site);
	    }

	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public Site getSite(@PathVariable("id") final int id) {
	        return siteService.getSite(id);
	    }

	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public Boolean deleteSite(@PathVariable("id") final int id) {
		 return siteService.deleteSite(id);
	    }
}
