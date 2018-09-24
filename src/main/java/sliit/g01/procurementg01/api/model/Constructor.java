package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Constructor")
public class Constructor extends AuthorizedEmployee {
	//add site
	private String site;

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	 
	
}   
