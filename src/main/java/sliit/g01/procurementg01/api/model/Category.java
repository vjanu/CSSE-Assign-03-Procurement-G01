package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Category")
public class Category {

	@Id
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

	public void selectCategory(String categoryId) {
		this.categoryId = categoryId;
	}

	public boolean addCategory(String categoryId, String categoryName) {
		return false;
	}

}
