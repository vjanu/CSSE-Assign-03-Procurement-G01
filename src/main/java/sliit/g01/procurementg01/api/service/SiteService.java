package sliit.g01.procurementg01.api.service;

import java.util.List;
import java.util.Map;

import sliit.g01.procurementg01.api.model.Site;

/**
 * created by viraj
 **/

public interface SiteService {

	List<Map<String, String>> getAvailableItems(String siteId);

	boolean addSite(Site site);

	List<Site> listAllSites();

	Site getSite(String siteId);

	boolean deleteSite(String siteId);

	Site updateSite(String siteId, Site site);

	Site getSiteUnderManager(String siteManagerId);

}
