package sliit.g01.procurementg01.api.service.impl;

import java.util.ArrayList;
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

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private SiteManagerServiceImpl siteManagerService;

	@Override
	public List<Item> getAvailableItems(String siteId) {
		return siteRepository.findBySiteId(siteId).getItems();
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
		List<Item> itemList = new ArrayList<>();

		// quantity required, mapped against item id.
		List<Item> itemIdAndQuantities = site.getItems();

		for (Item item : itemIdAndQuantities) {
			Item i = itemService.getItem(item.getItemId());
			if (i != null) {
				i.setQuantity(item.getQuantity());
				// create a new list of items for future use.
				itemList.add(i);
			}
		}
		site.setItems(itemList);
		return (siteRepository.save(site) != null);
	}

	@Override
	public boolean updateSite(String siteId, Site site) {
		Site newSite = siteRepository.findBySiteId(siteId);

		if (site.getSiteName() != null)
			newSite.setSiteName(site.getSiteName());

		if (site.getAddress() != null)
			newSite.setAddress(site.getAddress());

		if (site.getStorageCapacity() > 0)
			newSite.setStorageCapacity(site.getStorageCapacity());

		if (site.getCurrentCapacity() > 0)
			newSite.setCurrentCapacity(site.getCurrentCapacity());

		if (site.getSiteManager().getEmployeeId() != null)
			siteManagerService.assignSiteToSiteManager(site.getSiteId(), site.getSiteManager().getEmployeeId());

		if (site.getItems() != null)
			newSite.setItems(site.getItems());
		return (siteRepository.save(newSite) != null);
	}

	// returns the site which the provided site manager is managing currently.
	@Override
	public Site getSiteUnderManager(String siteManagerId) {
		return siteRepository.findSiteBySiteManager(siteManagerId);
	}

}
