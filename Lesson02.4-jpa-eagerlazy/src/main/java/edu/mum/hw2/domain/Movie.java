package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id @GeneratedValue
	private long id;
	private String title;
	@ElementCollection
	private List<Artist> artists = new ArrayList<Artist>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Artist> getArtists() {
		return artists;
	}

}
