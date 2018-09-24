package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.SiteManager;

/**
 * created by viraj
 **/

public interface SiteManagerService {

	SiteManager addSiteManager(SiteManager siteManager);

	SiteManager getSiteManagerOfSite(String managedSiteId);

	SiteManager getSiteManagerById(String employeeId);

	String getManagedSite(String siteManagerId);

	List<SiteManager> getSiteManagersOfSite(String managedSiteId); // use this
																	// if you
																	// believe
																	// there are
																	// multiple
																	// managers
																	// per site.

	List<SiteManager> getAllSiteManagers(); // basically everyone in the db.

	boolean assignSiteToSiteManager(String siteId, String employeeId);

}
