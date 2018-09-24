package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

}
