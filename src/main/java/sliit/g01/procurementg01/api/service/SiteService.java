package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.model.SiteManager;

/**
 * created by viraj
 **/

public interface SiteService {

	List<Item> getAvailableItems(int siteId);

	boolean addSite(Site site);

	List<Site> listAllSites();

	Site getSite(int siteId);

	Site getSiteUnderManager(String siteManagerId);


	boolean deleteSite(int siteId);

	Site updateSite(int siteId, Site site);

}
