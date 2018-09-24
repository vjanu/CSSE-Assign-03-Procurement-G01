package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author anushka
 *
 * NOTE: Since this is a child class, we can reserve a document,
 *       in mongodb under the class name to store records/data.
 */
@Document(collection = "SiteManager")
public class SiteManager extends AuthorizedEmployee {

    private String managedSiteId;


    public SiteManager() {}


    public String getManagedSiteId() {
        return managedSiteId;
    }

    public void setManagedSiteId(String managedSiteId) {
        this.managedSiteId = managedSiteId;
    }
}
