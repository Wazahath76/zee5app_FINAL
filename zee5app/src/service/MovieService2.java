package com.zee.zee5app.service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.repository.MovieRepository2;


public class MovieService2 {
	private MovieRepository2 repository = MovieRepository2.getInstance();
	private static MovieService2 service = null;		
	
	public static MovieService2 getInstance() {
		if(service == null)
			service = new MovieService2();
		return service;
	}
	
	public String addMovie(Movie movie) {
		return this.repository.addMovie(movie);
	}
	
	public Movie getMovieById(String id) {
		return this.repository.getMovieById(id);
	}
	
	public Movie[] getAllMovies() {
		return repository.getAllMovie();
	}

}
