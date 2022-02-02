package com.zee.zee5app.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.Impl.MovieRepositoryImpl;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository repository = MovieRepositoryImpl.getInstance();
	private static MovieService service;	
	
	public static MovieService getInstance() throws IOException {
		if(service == null)
			service = new MovieServiceImpl();
		return service;
	}
	
    private MovieServiceImpl() throws IOException{
		
	}

	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return this.repository.addMovie(movie);
	}

	@Override
	public String deleteMovie(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteMovie(id);
	}

	@Override
	public String modifyMovie(String id, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.modifyMovie(id, movie);
	}

	@Override
	public Optional<Movie> getMovieById(String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.repository.getMovieById(id);
	}

	@Override
	public Optional<List<Movie>> getAllMovie() throws NameNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		return this.repository.getAllMovie();
	}
    
    
	
//	@Override
//	public String addMovie(Movie movie) {
//		// TODO Auto-generated method stub
//		return this.repository.addMovie(movie);
//	}
//
//	@Override
//	public Movie getMovieById(String id) {
//		// TODO Auto-generated method stub
//		return this.repository.getMovieById(id);
//	}
//
//	@Override
//	public Movie[] getAllMovies() {
//		// TODO Auto-generated method stub
//		return repository.getAllMovie();
//	}

}
