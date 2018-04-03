package edu.mum.hw2.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CD")
public class CD extends Product {
	private String artist;
	
	public CD() {
		super();
	}

	public CD(String name, String description, String artist) {
		super(name, description);
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "CD [artist=" + artist + "]";
	}
}
