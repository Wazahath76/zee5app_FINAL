package com.zee.zee5app.repository.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.MovieRepository;


public class MovieRepositoryImpl implements MovieRepository {

	private Set<Movie> set = new LinkedHashSet<>();
	
	private MovieRepositoryImpl() {
		
	}
	
	private static MovieRepository movieRepository;
	public static MovieRepository getInstance() {
		if(movieRepository==null)
			movieRepository = new MovieRepositoryImpl();
		return movieRepository;
	}
	
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		boolean result = this.set.add(movie);
		if(!result) {
	
		return "fail";
		}
		return null;
	}

	@Override
	public String deleteMovie(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> optional = this.getMovieById(id);
		if (optional.isPresent()){
			boolean result = set.remove(optional.get());
		if(result) {
			return null;
		}
		else
			return("fail8");
	}
	return "fail8";
	}

	@Override
	public String modifyMovie(String id, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> optional = this.getMovieById(id);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			set.add(movie);
			if(result) {
				return null;
			}
			
		}
		return null;
	}

	@Override
	public Optional<Movie> getMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Movie movie2 = null;
		for (Movie movie : set) {
			if(movie.getId().equals(id)) {
				movie2 = movie;
			    break;
		}
			
		}
	 return Optional.of(Optional.ofNullable(movie2).orElseThrow(()-> new IdNotFoundException("movie id not found")));
				
	}

	@Override
	public List<Movie> getAllMovie() {
		// TODO Auto-generated method stub
        List<Movie> arrlist = new ArrayList(set);
        Collections.sort(arrlist);
        return arrlist;
        
//        return new ArrayList<>(set.descendingSet())
        

	}
	
	
}	
	
//	private Movie[] movies = new Movie[10];
//	private static int count = -1;
//	
//	private static MovieRepository movieRepository;
//	public static MovieRepository getInstance() {
//		if(movieRepository==null)
//			movieRepository = new MovieRepositoryImpl();
//		return movieRepository;
//	}
//	
//	private MovieRepositoryImpl() {
//		
//	}
//	
//	@Override
//	public String addMovie(Movie movie) {
//		// TODO Auto-generated method stub
//		if(count == movies.length-1) {
//			Movie temp[] = new Movie[movies.length*4];
//			System.arraycopy(movie, 0, temp, 0, movies.length);
//			movies = temp;
//			movies[++count] = movie;
//			return "Success2";	
//		}
//		movies[++count] = movie;
//		return "Success2";
//	}
//
//	@Override
//	public String deleteMovie(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String modifyMovie(String id, Movie movie) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Movie getMovieById(String id) {
//		// TODO Auto-generated method stub
//		for (Movie movie : movies) {
//			if(movie!=null && movie.getId().equals(id))
//				return movie;	
//		}
//		return null;
//	}
//
//	@Override
//	public Movie[] getAllMovie() {
//		// TODO Auto-generated method stub
//		return movies;
//	}
//
//}
