package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * created by viraj
 ***/
@Document(collection = "AccountingStaff")
public class AccountingStaff extends AuthorizedEmployee {

	@JsonIgnore
    private boolean deleted;
	private String category;

    public AccountingStaff() {}

    public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
