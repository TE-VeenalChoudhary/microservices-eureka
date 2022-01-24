package com.tyss.ratingsdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.ratingsdataservice.models.Rating;
import com.tyss.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

	@RequestMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	
	@RequestMapping("users/{userId}")
	public UserRating geRatings(@PathVariable("userId") String userId) {
		
		List<Rating> ratings = Arrays.asList(new Rating("3", 4), new Rating("5", 4));
		UserRating userRating = new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}

} 