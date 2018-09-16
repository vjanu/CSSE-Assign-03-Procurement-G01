package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.repository.SiteRepository;
import sliit.g01.procurementg01.api.service.SiteService;

/**
 * created by viraj
 **/

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Override
	public List<Item> getAvailableItems(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> listAllSites() {
		return siteRepository.findAll();
	}

	@Override
	public Site getSite(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteSite(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addSite(Site site) {
		return (siteRepository.save(site) != null);
	}

	@Override
	public Site updateSite(int siteId, Site site) {
		// TODO Auto-generated method stub
		return null;
	}

}
