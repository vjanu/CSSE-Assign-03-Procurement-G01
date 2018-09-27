package sliit.g01.procurementg01.api.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Category;
import sliit.g01.procurementg01.api.service.CategoryService;

/**
 * @author Tharindu TCJ
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add-new-category")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		category.setCategoryId("CT" + RandomStringUtils.randomNumeric(5));
		if (categoryService.addCategory(category)) {
			return new ResponseEntity<>("New Category Added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not Created", HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping("/")
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable String categoryId) {
		return categoryService.getCategory(categoryId);
	}
}
