package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.SiteManager;

import java.util.List;

public interface SiteManagerRepository extends MongoRepository<SiteManager, String> {

    // since site manager is the child of authorized employee class, we can use employeeId,
    // which is in the authorized employee class to search for the site manager as well.
    SiteManager findByEmployeeId(String employeeId);
    List<SiteManager> findAllByManagedSiteId(String managedSiteId);

}
