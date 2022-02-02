package com.zee.zee5app.repository.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.naming.NameNotFoundException;

import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
	
	private static MovieRepository repository;
	public static MovieRepository getInstance() throws IOException {
		if(repository==null)
			repository = new MovieRepositoryImpl();
		return repository;
	}
	
    private MovieRepositoryImpl() throws IOException{
		
	}
	
	DBUtils dbUtils = DBUtils.getInstance();

	
	
	
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertStatement = "insert into movies"
				+"(id, name, cast, length, releasedate, trailer, language, agelimit, genre)"
                +"values(?,?,?,?,?,?,?,?,?)";
		
        connection = dbUtils.getConnection();
        
        try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, movie.getId());
			preparedStatement.setString(2, movie.getMovieName());
			preparedStatement.setString(3, movie.getCast().toString());
			preparedStatement.setString(4, movie.getLength());
			preparedStatement.setString(5, movie.getReleaseDate());
			preparedStatement.setString(6, movie.getTrailer());
			preparedStatement.setString(7, movie.getLanguage());
			preparedStatement.setString(8, movie.getAgeLimit());
			preparedStatement.setString(9, movie.getGenre());
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "movie added successfully";
			}
			else {
				connection.rollback();
				return "fail19";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail11";
			}
			
			e.printStackTrace();
			return "fail11";
		}
        finally {
        	dbUtils.closeConnection(connection);
        }
	}

	@Override
	public String deleteMovie(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteStatement = "delete from movies where id = ?";
		
        connection = dbUtils.getConnection();
        
        try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "movie deleted successfully";
			}
			else {
				connection.rollback();
				return "fail20";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail20";
			}
			e.printStackTrace();
			return "fail20";
		}
        finally {
        	dbUtils.closeConnection(connection);
        }
	}

	@Override
	public String modifyMovie(String id, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateStatement = "UPDATE movies SET name = ?, length=?, agelimit =? where id = ?";
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, movie.getMovieName());
			preparedStatement.setString(2, movie.getLength());
			preparedStatement.setString(3, movie.getAgeLimit());
			preparedStatement.setString(4, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "movie updated successfully";
			}
			else {
				connection.rollback();
				return "fail21";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail21";
			}
			e.printStackTrace();
			return "fail21";
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		
	}

	@Override
	public Optional<Movie> getMovieById(String id) throws IdNotFoundException, InvalidIdLengthException, NameNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "select * from movies where id=?";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Movie movie = new Movie();
				movie.setId(resultSet.getString("id"));
				movie.setAgeLimit(resultSet.getString("agelimit"));
				movie.setCast(resultSet.getString("cast"));
				movie.setGenre(resultSet.getString("genre"));
				movie.setLanguage(resultSet.getString("language"));
				movie.setLength(resultSet.getString("length"));
				movie.setMovieName(resultSet.getString("name"));
				movie.setReleaseDate(resultSet.getString("releasedate"));
				movie.setTrailer(null);
				
				return Optional.of(movie);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Movie>> getAllMovie() throws InvalidIdLengthException, NameNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Movie> arraylist = new ArrayList<>();
		String selectStatement = "select * from movies";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Movie movie = new Movie();
				movie.setId(resultSet.getString("id"));
				movie.setAgeLimit(resultSet.getString("agelimit"));
				movie.setCast(resultSet.getString("cast"));
				movie.setGenre(resultSet.getString("genre"));
				movie.setLanguage(resultSet.getString("language"));
				movie.setLength(resultSet.getString("length"));
				movie.setMovieName(resultSet.getString("name"));
				movie.setReleaseDate(resultSet.getString("releasedate"));
				movie.setTrailer(null);
				arraylist.add(movie);
				
				
			}
			return Optional.ofNullable(arraylist);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
		
	}

	
	
	
//	private Set<Movie> set = new LinkedHashSet<>();
//	
//	private MovieRepositoryImpl() {
//		
//	}
//	
//	private static MovieRepository movieRepository;
//	public static MovieRepository getInstance() {
//		if(movieRepository==null)
//			movieRepository = new MovieRepositoryImpl();
//		return movieRepository;
//	}
//	
//	@Override
//	public String addMovie(Movie movie) {
//		// TODO Auto-generated method stub
//		boolean result = this.set.add(movie);
//		if(!result) {
//	
//		return "fail";
//		}
//		return null;
//	}
//
//	@Override
//	public String deleteMovie(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Movie> optional = this.getMovieById(id);
//		if (optional.isPresent()){
//			boolean result = set.remove(optional.get());
//		if(result) {
//			return null;
//		}
//		else
//			return("fail8");
//	}
//	return "fail8";
//	}
//
//	@Override
//	public String modifyMovie(String id, Movie movie) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Movie> optional = this.getMovieById(id);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			set.add(movie);
//			if(result) {
//				return null;
//			}
//			
//		}
//		return null;
//	}
//
//	@Override
//	public Optional<Movie> getMovieById(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Movie movie2 = null;
//		for (Movie movie : set) {
//			if(movie.getId().equals(id)) {
//				movie2 = movie;
//			    break;
//		}
//			
//		}
//	 return Optional.of(Optional.ofNullable(movie2).orElseThrow(()-> new IdNotFoundException("movie id not found")));
//				
//	}
//
//	@Override
//	public List<Movie> getAllMovie() {
//		// TODO Auto-generated method stub
//        List<Movie> arrlist = new ArrayList(set);
//        Collections.sort(arrlist);
//        return arrlist;
//        
////        return new ArrayList<>(set.descendingSet())
//        
//
//	}
	
	
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
