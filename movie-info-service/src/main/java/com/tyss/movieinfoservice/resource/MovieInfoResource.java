package com.tyss.movieinfoservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tyss.movieinfoservice.models.Movie;
import com.tyss.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
//	@RequestMapping("/{movieId}")
//	public Movie getMovieInfo(@PathVariable String movieId) {
//		
//		return new Movie(movieId, "Test");
//	}
	
	@Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

	@RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }
}
