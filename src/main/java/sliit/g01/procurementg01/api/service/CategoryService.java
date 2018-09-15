package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Category;

public interface CategoryService {

	Boolean addCategory(Category category);

	List<Category> getAllCategories();

	Category getCategory(String categoryId);

	Boolean deleteCategory(String categoryId);
}
