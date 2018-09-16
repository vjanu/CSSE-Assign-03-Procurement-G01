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
@Service
public class SiteManagerServiceImpl implements SiteManagerService {

    @Autowired
    private SiteManagerRepository siteManagerRepository;

	@Override
	public SiteManager addSiteManager(SiteManager siteManager) {
		return siteManagerRepository.save(siteManager);
	}

	
	
}
