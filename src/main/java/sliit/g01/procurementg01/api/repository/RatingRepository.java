package sliit.g01.procurementg01.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.Rating;
/**
 * @author Viraj
 */
public interface RatingRepository extends MongoRepository<Rating, String> {
	
//	List<Rating> getSupplierRatings();
}
