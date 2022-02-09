package com.zee.zee5app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;


public interface SeriesRepository {
	
	public String addSeries(Series series); 
	public String deleteSeries(String id) throws IdNotFoundException;
	public String modifySeries(String id, Series series) throws IdNotFoundException;
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException;
	public List<Series> getAllSeries();

}
