package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;

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
    }

