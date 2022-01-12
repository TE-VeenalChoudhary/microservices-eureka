package com.tyss.movieinfoservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		
		return new Movie(movieId, "Test");
	}

}
