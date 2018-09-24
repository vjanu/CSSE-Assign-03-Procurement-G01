package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.Category;

/**
 * @author Tharindu
 **/
public interface CategoryRepository extends MongoRepository<Category, String> {
	Category findByCategoryId(String categoryId);
}
