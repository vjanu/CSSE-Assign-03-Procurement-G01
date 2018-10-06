package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.SiteManager;

/**
 * @author Viraj
 */

public interface SiteManagerService {

	SiteManager addSiteManager(SiteManager siteManager);

	SiteManager getSiteManagerOfSite(String managedSiteId);

	SiteManager getSiteManagerById(String employeeId);

	String getManagedSite(String siteManagerId);

	// use this if you believe there are multiple managers per site.
	List<SiteManager> getSiteManagersOfSite(String managedSiteId);

	// basically everyone in the db.
	List<SiteManager> getAllSiteManagers();

	boolean assignSiteToSiteManager(String siteId, String employeeId);

	List<SiteManager> getUnAssignedSiteManagers();

}
