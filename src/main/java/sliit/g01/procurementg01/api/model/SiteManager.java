package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

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
