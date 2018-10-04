package sliit.g01.procurementg01.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Rating;
import sliit.g01.procurementg01.api.repository.RatingRepository;
import sliit.g01.procurementg01.api.service.RatingService;

/**
 * @author Viraj/Tharindu
 */

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
		ArrayList<Rating> r =  new ArrayList<Rating>();
		
		for (Rating rating : ratingRepository.findAll()) {
			if(rating.getConstructorId() != null){
				r.add(rating);
			}
		}
		return r;
	}

	@Override
	public List<Rating> getSupplierRatings() {
		ArrayList<Rating> r =  new ArrayList<Rating>();
		
		for (Rating rating : ratingRepository.findAll()) {
			if(rating.getSupplierId() != null){
				r.add(rating);
			}
		}
		return r;
	}
}