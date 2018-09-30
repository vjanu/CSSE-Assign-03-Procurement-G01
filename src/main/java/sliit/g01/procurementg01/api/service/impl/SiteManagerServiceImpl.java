package sliit.g01.procurementg01.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;
import sliit.g01.procurementg01.api.repository.SiteRepository;
import sliit.g01.procurementg01.api.service.SiteManagerService;

/**
 * created by viraj
 **/
@Service("SiteManagerService")
public class SiteManagerServiceImpl implements SiteManagerService {

	@Autowired
	private SiteManagerRepository siteManagerRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Override
	public SiteManager addSiteManager(SiteManager siteManager) {
		return siteManagerRepository.save(siteManager);
	}

	@Override
	public String getManagedSite(String siteManagerId) {
		// check if a site manager exists under the given employee id to avoid
		// null pointers.
		SiteManager siteManager = siteManagerRepository.findByEmployeeId(siteManagerId);
		String managedSiteId = siteManager != null ? siteManager.getManagedSiteId() : "";

		return managedSiteId;
	}

	@Override
	public SiteManager getSiteManagerOfSite(String managedSiteId) {
		// check if a manager exists for a given site id to avoid null pointers.
		SiteManager siteManager = siteManagerRepository.findSiteManagerByManagedSiteId(managedSiteId);

		if (siteManager == null) {
			siteManager = new SiteManager();
		}

		return siteManager;
	}

	@Override
	public List<SiteManager> getSiteManagersOfSite(String managedSiteId) {
		return siteManagerRepository.findAllByManagedSiteId(managedSiteId);
	}

	@Override
	public SiteManager getSiteManagerById(String employeeId) {
		return siteManagerRepository.findByEmployeeId(employeeId);
	}

	// this will assign a site to an existing manager.
	// this goes both ways => site manager is assigned to the site and the site
	// is,
	// assigned to the manager.
	@Override
	public boolean assignSiteToSiteManager(String siteId, String employeeId) {
		// first update the site manager object in the database since we need,
		// an updated record in the db when we are inserting that to the,
		// site's record.
		SiteManager siteManager = siteManagerRepository.findByEmployeeId(employeeId);
		Site site = siteRepository.findSiteBySiteId(siteId);

		// we only proceed if both the site manager and the site actually exist.
		if (siteManager != null && site != null) {
			siteManager.setManagedSiteId(siteId);
			siteManagerRepository.save(siteManager);

			// now we update the site with its new manager.
			site.setSiteManager(siteManager);
			siteRepository.save(site);

			return true;
		}

		return false;
	}

	@Override
	public List<SiteManager> getAllSiteManagers() {
		return siteManagerRepository.findAll();
	}

	@Override
	public List<SiteManager> getUnAssignedSiteManagers() {
		List<SiteManager> siteManagerList = siteManagerRepository.findAll();
		List<Site> siteList = siteRepository.findAll();

		List<SiteManager> unassignedList = new ArrayList<SiteManager>();

		for (SiteManager sm : siteManagerList) {
			for (Site s : siteList) {
				if (!sm.getEmployeeId().equals(s.getSiteManager().getEmployeeId())) {
					unassignedList.add(sm);
				}
			}
		}

		return unassignedList;
	}
}
