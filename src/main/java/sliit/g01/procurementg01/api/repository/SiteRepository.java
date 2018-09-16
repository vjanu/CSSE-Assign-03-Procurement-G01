package sliit.g01.procurementg01.api.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.Site;

/**
 * created by viraj
 **/

public interface SiteRepository extends MongoRepository<Site, String> {


}
