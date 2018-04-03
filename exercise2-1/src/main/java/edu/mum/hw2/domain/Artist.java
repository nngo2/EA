package edu.mum.hw2.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Artist {
	private String name;
	private int rating;
	private String artistCharacter;
	
	public Artist() {
		super();
	}
	
	public Artist(String name, int rating, String artistCharacter) {
		super();
		this.name = name;
		this.rating = rating;
		this.artistCharacter = artistCharacter;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getArtistCharacter() {
		return artistCharacter;
	}
	public void setArtistCharacter(String artistCharacter) {
		this.artistCharacter = artistCharacter;
	}
}
