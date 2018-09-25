package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.Site;

/**
 * created by viraj
 **/

public interface SiteRepository extends MongoRepository<Site, String> {
	Site findBySiteId(String siteId);

	Site findSiteBySiteId(String siteId); // use this since the model has a
											// String for siteId.

	Site findSiteBySiteManager(String siteManagerId);
	// Item findByDiteId(String siteId);

}
