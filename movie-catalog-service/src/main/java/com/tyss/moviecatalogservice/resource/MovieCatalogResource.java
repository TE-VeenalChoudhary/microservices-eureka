package com.tyss.moviecatalogservice.resource;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.tyss.moviecatalogservice.models.CatalogItem;
import com.tyss.moviecatalogservice.models.Movie;
import com.tyss.moviecatalogservice.models.Rating;
import com.tyss.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder builder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		
		

//		Movie movie = restTemplate.getForObject("http://localhost:8081/movies/", Movie.class);

//		get all rated movieIds
//		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 4)

//		);

		//for each movieId, call movie info service and get details
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		return userRating.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

			/*
			 * Movie movie = builder.build().get().uri("http://localhost:8081/movies/" +
			 * rating.getMovieId()).retrieve() .bodyToMono(Movie.class).block();
			 */
			
			//put them all together
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());
	}

//		return Collections.singletonList(new CatalogItem("KGF", "Test", 4));
}
