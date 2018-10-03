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
	public List<Item> getAvailableItems(String siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> listAllSites() {
		return siteRepository.findAll();
	}

	@Override
	public Site getSite(String siteId) {
		return siteRepository.findBySiteId(siteId);
	}

	@Override
	public boolean deleteSite(String siteId) {
		siteRepository.delete(siteRepository.findBySiteId(siteId));
		return true;
	}

	@Override
	public boolean addSite(Site site) {
		return (siteRepository.save(site) != null);
	}

	@Override
	public Site updateSite(String siteId, Site site) {
		Site newSite = siteRepository.findBySiteId(siteId);

		if (site.getItems() != null)
			newSite.setItems(site.getItems());
		return siteRepository.save(newSite);
	}

	// returns the site which the provided site manager is managing currently.
	@Override
	public Site getSiteUnderManager(String siteManagerId) {
		return siteRepository.findSiteBySiteManager(siteManagerId);
	}

}
