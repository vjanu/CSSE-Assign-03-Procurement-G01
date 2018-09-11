package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.SiteManager;

import java.util.List;

public interface SiteManagerRepository extends MongoRepository<SiteManager, String> {

    SiteManager findByEmployeeId(String employeeId);
    List<SiteManager> findAllByManagedSiteId(String managedSiteId);

}
