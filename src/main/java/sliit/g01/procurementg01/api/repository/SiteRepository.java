package sliit.g01.procurementg01.api.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.Site;

/**
 * @author viraj
 * 
 * @author Tharindu TCJ
 **/

public interface SiteRepository extends MongoRepository<Site, String> {
	Site findBySiteId(String siteId);

	Site findSiteBySiteId(String siteId); // use this since the model has a
											// String for siteId.

	Site findSiteBySiteManager(String siteManagerId);
	// Item findByDiteId(String siteId);

	List<Map<String, String>> findItemBySiteId(String siteId);

}
