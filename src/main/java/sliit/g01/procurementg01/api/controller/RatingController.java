package sliit.g01.procurementg01.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sliit.g01.procurementg01.api.model.Rating;
import sliit.g01.procurementg01.api.service.RatingService;

/**
 * created by viraj
 **/
@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addRating(@RequestBody Rating rating) {
		if (ratingService.addRatings(rating)) {
			return new ResponseEntity<>("Rating added", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Rating not added", HttpStatus.NOT_IMPLEMENTED);
		}
	}

}
