package sliit.g01.procurementg01.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Rating;
import sliit.g01.procurementg01.api.model.RequestMaterial;
import sliit.g01.procurementg01.api.repository.RatingRepository;
import sliit.g01.procurementg01.api.repository.RequestMaterialRepository;
import sliit.g01.procurementg01.api.service.RatingService;
import sliit.g01.procurementg01.api.service.RequestMaterialService;

import java.util.List;

/**
 * created by viraj
 **/

@Service("ratingService")
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;


	@Override
	public boolean addRatings(Rating rating) {
		return (ratingRepository.save(rating) != null);
	}
}
