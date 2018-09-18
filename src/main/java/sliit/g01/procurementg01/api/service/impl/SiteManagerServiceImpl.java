package sliit.g01.procurementg01.api.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.repository.AccountingStaffRepository;
import sliit.g01.procurementg01.api.repository.SiteManagerRepository;
import sliit.g01.procurementg01.api.service.AccountingStaffService;
import sliit.g01.procurementg01.api.service.SiteManagerService;
/**
 * created by viraj
 **/
@Service("SiteManagerService")
public class SiteManagerServiceImpl implements SiteManagerService {

    @Autowired
    private SiteManagerRepository siteManagerRepository;

	@Override
	public SiteManager addSiteManager(SiteManager siteManager) {
		return siteManagerRepository.save(siteManager);
	}

	@Override
	public String getManagedSite(String siteManagerId) {
	    // check if a site manager exists under the given employee id to avoid null pointers.
		SiteManager siteManager = siteManagerRepository.findByEmployeeId(siteManagerId);
		String managedSiteId = siteManager != null ? siteManager.getManagedSiteId() : "";

		return managedSiteId;
	}

	@Override
	public SiteManager getSiteManagerOfSite(String managedSiteId) {
		// check if a manager exists for a given site id to avoid null pointers.
	    SiteManager siteManager = siteManagerRepository.findSiteManagerByManagedSiteId(managedSiteId);

	    if (siteManager == null) {
	        siteManager  = new SiteManager();
        }

        return siteManager;
	}

    @Override
    public List<SiteManager> getSiteManagersOfSite(String managedSiteId) {
        return siteManagerRepository.findAllByManagedSiteId(managedSiteId);
    }

}
