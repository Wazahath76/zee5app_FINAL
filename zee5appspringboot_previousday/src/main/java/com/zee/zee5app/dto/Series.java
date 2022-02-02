package com.zee.zee5app.dto;

import java.net.URL;
import java.lang.*;
import javax.naming.NameNotFoundException;

import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;
//@Data
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Series implements Comparable<Series> {
	
	@Setter(value = AccessLevel.NONE)
	private String seriesName;
	
	private String length;
	
	@Setter(value = AccessLevel.NONE)
	private String id;
	
	private String genre;
	private String releaseDate;
	private String trailer;
	private String Cast;
	private String ageLimit;
	private int noofepisodes;
	private String language;
	
	public Series(String id, String seriesName, String cast,  String length, String releaseDate, String trailer,
			String language, String ageLimit, String genre, int noofepisodes) throws InvalidIdLengthException, NameNotFoundException {
		super();
		this.setId(id);
		this.setSeriesName(seriesName);
		this.setAgeLimit(ageLimit);
		this.setCast(cast);
		this.setGenre(genre);
		
		this.setLength(length);
		this.setReleaseDate(releaseDate);
		this.setTrailer(trailer);
		this.setNoofepisodes(noofepisodes);
		this.setLanguage(language);
	}
	
	public Series() {
		
	}


	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}

	public void setSeriesName(String seriesName) throws NameNotFoundException {

		if(seriesName == null)
			throw new NameNotFoundException("series name not found");
		this.seriesName = seriesName;
	}

	public void setId(String id) throws InvalidIdLengthException {
		if(id.length()<6)
			throw new InvalidIdLengthException("id length less than 6");
		this.id = id;
	}
	
}