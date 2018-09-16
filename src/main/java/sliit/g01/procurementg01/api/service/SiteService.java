package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Category;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;
/**
 * created by viraj
 **/

public interface SiteService {


	List<Item> getAvailableItems(int siteId);
	Site addSite(Site site);
	List<Site> listAllSites();
	Site getSite(int siteId);
	Boolean deleteSite(int siteId);
	Site updateSite(int siteId, Site site);


}
