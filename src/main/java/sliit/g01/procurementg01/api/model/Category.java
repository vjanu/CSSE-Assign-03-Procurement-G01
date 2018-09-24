package sliit.g01.procurementg01.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tharindu
 **/
@Document(collection = "Category")
public class Category {

	private String categoryId;
	private String categoryName;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
