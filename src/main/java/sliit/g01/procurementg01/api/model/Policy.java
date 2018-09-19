package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author anushka
 *
 * This is responsible for representing company policies;
 * during the ordering process, existing policies should be checked,
 * for any potential violation.
 */
@Document(collection = "Policy")
public class Policy {
    @Id
    private String policyId;
    private String description;


    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
