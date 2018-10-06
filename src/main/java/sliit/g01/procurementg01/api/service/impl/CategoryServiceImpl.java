package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Category;
import sliit.g01.procurementg01.api.repository.CategoryRepository;
import sliit.g01.procurementg01.api.service.CategoryService;

/**
 * @author Tharindu
 **/
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Boolean addCategory(Category category) {
		return (categoryRepository.save(category) != null);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(String categoryId) {
		return categoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public Boolean deleteCategory(String categoryId) {
		return null;
	}

}
