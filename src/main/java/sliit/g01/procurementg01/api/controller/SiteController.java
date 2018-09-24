package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.service.SiteService;
import sliit.g01.procurementg01.api.service.impl.SiteManagerServiceImpl;

/**
 * created by viraj
 **/
@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private SiteService siteService;

    @Autowired
    private SiteManagerServiceImpl siteManagerService;


    @RequestMapping(value = "/add-new-site", method = RequestMethod.POST)
	public ResponseEntity<String> addSite(@Validated @RequestBody Site site) {
		if (siteService.addSite(site)) {
            // update the site with its new site manager's details.
            // when sending data, we only send site manager's employeeId enclosed in siteManager attribute.
            // assignSiteToSiteManager method will take care of adding all details of the site manager to the,
            // site's record while updating the managed site id in the site manager's entry in the database.
            siteManagerService.assignSiteToSiteManager(site.getSiteId(), site.getSiteManager().getEmployeeId());    // in site object, we have included a site manager object which has the employee id.
			return new ResponseEntity<>("New Site Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not Created", HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
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
