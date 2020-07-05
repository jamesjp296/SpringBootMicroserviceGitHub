package io.myspringmicroservices.ratingsdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.myspringmicroservices.ratingsdataservice.models.Rating;
import io.myspringmicroservices.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping("{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId ) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratingsList = Arrays.asList(new Rating("New 1234", 4), new Rating("New 5647", 5));
		
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratingsList);
		return userRating;
	}
}
