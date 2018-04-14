package edu.mum.cs544.imdb.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Studio {
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	private String description;
	
	private String location;
	
	@OneToMany(mappedBy="studio")
	private List<TVSeries> tvSeriesList;
	
	public List<TVSeries> getTVSeriesList() {
		return Collections.unmodifiableList(tvSeriesList);
	}
	
	public void addTVSeries(TVSeries tvSeries) {
		tvSeries.setStudio(this);
		tvSeriesList.add(tvSeries);
	}
	
	public void removeTVSeries(TVSeries tvSeries) {
		tvSeries.setStudio(null);
		tvSeriesList.remove(tvSeries);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
