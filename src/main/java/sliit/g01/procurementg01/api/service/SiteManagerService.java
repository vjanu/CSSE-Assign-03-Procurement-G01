package sliit.g01.procurementg01.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.model.SiteManager;
/**
 * created by viraj
 **/


public interface SiteManagerService {

	SiteManager addSiteManager(SiteManager siteManager);

	SiteManager getSiteManagerOfSite(String managedSiteId);

	SiteManager getSiteManagerById(String employeeId);

	String getManagedSite(String siteManagerId);

	List<SiteManager> getSiteManagersOfSite(String managedSiteId);  // use this if you believe there are multiple managers per site.

    boolean assignSiteToSiteManager(String siteId, String employeeId);
}
