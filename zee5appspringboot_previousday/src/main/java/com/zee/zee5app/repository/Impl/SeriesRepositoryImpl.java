package com.zee.zee5app.repository.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.SeriesRepository;

@Repository
public class SeriesRepositoryImpl implements SeriesRepository {
	
	
	@Autowired //it will bring already created object either by using name/type
    DataSource dataSource;
	
	public SeriesRepositoryImpl() throws IOException {
		
	}

	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertStatement = "insert into series"
				+"(id, name, cast, length, releasedate, trailer, language, agelimit, genre, noofepisodes)"
                +"values(?,?,?,?,?,?,?,?,?,?)";
		
        try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, series.getId());
			preparedStatement.setString(2, series.getSeriesName());
			preparedStatement.setString(3, series.getCast().toString());
			preparedStatement.setString(4, series.getLength());
			preparedStatement.setString(5, series.getReleaseDate());
			preparedStatement.setString(6, series.getTrailer());
			preparedStatement.setString(7, series.getLanguage());
			preparedStatement.setString(8, series.getAgeLimit());
			preparedStatement.setString(9, series.getGenre());
			preparedStatement.setInt(10, series.getNoofepisodes());
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				//connection.commit();
				return "series added successfully";
			}
			else {
				connection.rollback();
				return "fail24";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail24";
			}
			
			e.printStackTrace();
			return "fail24";
		}
        
	}

	@Override
	public String deleteSeries(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteStatement = "delete from series where id = ?";
		
        try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				//connection.commit();
				return "series deleted successfully";
			}
			else {
				connection.rollback();
				return "fail25";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail25";
			}
			e.printStackTrace();
			return "fail25";
		}
        
	}

	@Override
	public String modifySeries(String id, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateStatement = "UPDATE series SET name = ?, length=?, agelimit =? where id = ?";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, series.getSeriesName());
			preparedStatement.setString(2, series.getLength());
			preparedStatement.setString(3, series.getAgeLimit());
			preparedStatement.setString(4, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				//connection.commit();
				return "series updated successfully";
			}
			else {
				connection.rollback();
				return "fail26";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail26";
			}
			e.printStackTrace();
			return "fail26";
		}
		
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, NameNotFoundException, InvalidIdLengthException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "select * from series where id=?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Series series = new Series();
				series.setId(resultSet.getString("id"));
				series.setAgeLimit(resultSet.getString("agelimit"));
				series.setCast(resultSet.getString("cast"));
				series.setGenre(resultSet.getString("genre"));
				series.setLanguage(resultSet.getString("language"));
				series.setLength(resultSet.getString("length"));
				series.setSeriesName(resultSet.getString("name"));
				series.setReleaseDate(resultSet.getString("releasedate"));
				series.setTrailer(null);
				series.setNoofepisodes(resultSet.getInt("noofepisodes"));
				
				return Optional.of(series);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeries() throws InvalidIdLengthException, NameNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Series> arraylist = new ArrayList<>();
		String selectStatement = "select * from series";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Series series = new Series();
				series.setId(resultSet.getString("id"));
				series.setAgeLimit(resultSet.getString("agelimit"));
				series.setCast(resultSet.getString("cast"));
				series.setGenre(resultSet.getString("genre"));
				series.setLanguage(resultSet.getString("language"));
				series.setLength(resultSet.getString("length"));
				series.setSeriesName(resultSet.getString("name"));
				series.setReleaseDate(resultSet.getString("releasedate"));
				series.setTrailer(null);
				series.setNoofepisodes(resultSet.getInt("noofepisodes"));
				arraylist.add(series);
				
				
			}
			return Optional.ofNullable(arraylist);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
    
	
	
	
	
//	private TreeSet<Series> set = new TreeSet<>();
//	
//	private static SeriesRepository seriesrepository;
//	public static SeriesRepository getInstance() {
//		if(seriesrepository==null)
//			seriesrepository = new SeriesRepositoryImpl();
//		return seriesrepository;
//	}
//	
//	private SeriesRepositoryImpl() {
//		
//	}
//
//	@Override
//	public String addSeries(Series series) {
//		// TODO Auto-generated method stub
//       boolean result = this.set.add(series);
//		if(result) {
//			return null;
//		}
//		return "fail1";	
//	}
//
//	@Override
//	public String deleteSeries(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Series> optional = this.getSeriesById(id);
//		if (optional.isPresent()){
//			boolean result = set.remove(optional.get());
//		if(result) {
//			return (null);
//		}
//		else
//			return("fail9");
//	}
//	return "fail9";
//	}
//
//	@Override
//	public String modifySeries(String id, Series series) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Series> optional = this.getSeriesById(id);
//		if(optional.isPresent()) {
//			boolean result = set.remove(optional.get());
//			set.add(series);
//			if(result) {
//				return null;
//			}
//			
//		}
//		return null;
//	}
//
//	@Override
//	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Series series2 = null;
//		for (Series series : set) {
//			if(series.getId().equals(id)) {
//				series2 = series;
//			    break;
//		}
//			
//		}
//	 return Optional.of(Optional.ofNullable(series2).orElseThrow(()-> new IdNotFoundException("series id not found")));
//	}
//
//	@Override
//	public List<Series> getAllSeries() {
//		// TODO Auto-generated method stub
//        List<Series> arrlist = new ArrayList<>(set);
//        Collections.sort(arrlist);
//        return arrlist;
//	}
//	
	
	
	
//	@Override
//	public String addSeries(Series series) {
//		// TODO Auto-generated method stub
//		if(count == seriess.length-1) {
//			Series temp[] = new Series[seriess.length*4];
//			System.arraycopy(series, 0, temp, 0, seriess.length);
//			seriess = temp;
//			seriess[++count] = series;
//			
//			return "Success4";
//		}
//		seriess[++count] = series;
//		return "Success4";
//	}
//
//	@Override
//	public String deleteSeries(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String modifySeries(String id, Series series) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Series getSeriesById(String id) {
//		// TODO Auto-generated method stub
//		for (Series series : seriess) {
//			if(series!=null && series.getId().equals(id))
//				return series;
//		}
//		return null;
//	}
//
//	@Override
//	public Series[] getAllSeries() {
//		// TODO Auto-generated method stub
//		return seriess;
//	}

}
