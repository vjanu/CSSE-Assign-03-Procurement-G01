package sliit.g01.procurementg01.api.utility;

import java.util.ArrayList;
import java.util.List;

import sliit.g01.procurementg01.api.model.Site;

/**
 * @author Tharindu TCJ
 */
public class SiteUtils {

	protected final String ADD_NEW_SITE_JSON = "{\"siteId\":\"ST1021\",\"siteName\":\"Malabe\","
			+ "\"address\":\"27/g Malabe\",\"storageCapacity\":21,\"currentCapacity\":200}";

	private Site createSiteBean(String siteId, String siteName, String address, float storageCapacity,
			long currentCapacity) {
		Site site = new Site();
		site.setSiteId(siteId);
		site.setSiteName(siteName);
		site.setAddress(address);
		site.setStorageCapacity(storageCapacity);
		site.setCurrentCapacity(currentCapacity);
		return site;
	}

	protected List<Site> getSiteBeans() {
		List<Site> siteManagerList = new ArrayList<>();
		siteManagerList.add(createSiteBean("ST1211", "Malabe", "21/g Malabe", 12.0f, 90));
		return siteManagerList;
	}

}
