package com.zee.zee5app.repository.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;

public class SeriesRepositoryImpl implements SeriesRepository {

	private TreeSet<Series> set = new TreeSet<>();
	
	private static SeriesRepository seriesrepository;
	public static SeriesRepository getInstance() {
		if(seriesrepository==null)
			seriesrepository = new SeriesRepositoryImpl();
		return seriesrepository;
	}
	
	private SeriesRepositoryImpl() {
		
	}

	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
       boolean result = this.set.add(series);
		if(result) {
			return null;
		}
		return "fail1";	
	}

	@Override
	public String deleteSeries(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Series> optional = this.getSeriesById(id);
		if (optional.isPresent()){
			boolean result = set.remove(optional.get());
		if(result) {
			return (null);
		}
		else
			return("fail9");
	}
	return "fail9";
	}

	@Override
	public String modifySeries(String id, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Series> optional = this.getSeriesById(id);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			set.add(series);
			if(result) {
				return null;
			}
			
		}
		return null;
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Series series2 = null;
		for (Series series : set) {
			if(series.getId().equals(id)) {
				series2 = series;
			    break;
		}
			
		}
	 return Optional.of(Optional.ofNullable(series2).orElseThrow(()-> new IdNotFoundException("series id not found")));
	}

	@Override
	public List<Series> getAllSeries() {
		// TODO Auto-generated method stub
        List<Series> arrlist = new ArrayList<>(set);
        Collections.sort(arrlist);
        return arrlist;
	}
	
	
	
	
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
