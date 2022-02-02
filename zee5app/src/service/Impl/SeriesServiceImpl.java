package com.zee.zee5app.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.repository.Impl.SeriesRepositoryImpl;

public class SeriesServiceImpl implements SeriesService {

	private SeriesRepository repository = SeriesRepositoryImpl.getInstance();
	private static SeriesService service;	
	
	public static SeriesService getInstance() {
		if(service == null)
			service = new SeriesServiceImpl();
		return service;
	}
	
    private SeriesServiceImpl() {
		
	}

	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		return this.repository.addSeries(series);
	}

	@Override
	public String deleteSeries(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteSeries(id);
	}

	@Override
	public String modifySeries(String id, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.modifySeries(id, series);
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.getSeriesById(id);
	}

	@Override
	public List<Series> getAllSeries() {
		// TODO Auto-generated method stub
		return this.repository.getAllSeries();
	}
	
//	@Override
//	public String addSeries(Series series) {
//		// TODO Auto-generated method stub
//		return this.repository.addSeries(series);
//	}
//
//	@Override
//	public Series getSeriesById(String id) {
//		// TODO Auto-generated method stub
//		return this.repository.getSeriesById(id);
//	}
//
//	@Override
//	public Series[] getAllSeries() {
//		// TODO Auto-generated method stub
//		return repository.getAllSeries();
//	}

}
