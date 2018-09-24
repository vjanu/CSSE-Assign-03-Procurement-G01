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

/**
 * created by viraj
 **/
@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "/add-new-site", method = RequestMethod.POST)
	public ResponseEntity<String> addSite(@Validated @RequestBody Site site) {
		if (siteService.addSite(site)) {
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
	public List<Item> getAvailableItems(@PathVariable("id") final String id) {
		return siteService.getAvailableItems(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Site updateSite(@PathVariable("id") final String id, @Validated @RequestBody final Site site) {
		return siteService.updateSite(id, site);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Site getSite(@PathVariable("id") final String id) {
		return siteService.getSite(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean deleteSite(@PathVariable("id") final String id) {
		return siteService.deleteSite(id);
	}
}
