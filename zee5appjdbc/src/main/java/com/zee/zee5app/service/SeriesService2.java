package com.zee.zee5app.service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.repository.SeriesRepository2;


public class SeriesService2 {
	
	private SeriesRepository2 repository = SeriesRepository2.getInstance();
	private static SeriesService2 service = null;		
	
	public static SeriesService2 getInstance() {
		if(service == null)
			service = new SeriesService2();
		return service;
	}
	
	public String addSeries(Series series) {
		return this.repository.addSeries(series);
	}
	
	public Series getSeriesById(String id) {
		return this.repository.getSeriesById(id);
	}
	
	public Series[] getAllSeries() {
		return repository.getAllSeries();
	}


}