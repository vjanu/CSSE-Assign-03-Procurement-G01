package sliit.g01.procurementg01.api.model;

import org.springframework.data.annotation.Id;

public class Category {
	
	@Id
    private String categoryId;
    private String categoryName;
    
    public void selectCategory(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean addCategory(String categoryId, String categoryName) {
        return false;
    }

}
