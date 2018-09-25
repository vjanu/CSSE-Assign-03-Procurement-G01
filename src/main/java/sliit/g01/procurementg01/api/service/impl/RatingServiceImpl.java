package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Rating;
import sliit.g01.procurementg01.api.repository.RatingRepository;
import sliit.g01.procurementg01.api.service.RatingService;

/**
 * created by viraj & Modified by Tharindu
 **/

@Service("ratingService")
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public boolean addRatings(Rating rating) {
		return (ratingRepository.save(rating) != null);
	}

	@Override
	public List<Rating> getConstructorRatings() {
		return ratingRepository.findAll();
	}
}