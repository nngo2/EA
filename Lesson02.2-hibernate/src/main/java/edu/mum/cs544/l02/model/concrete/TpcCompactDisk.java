package edu.mum.cs544.l02.model.concrete;

import javax.persistence.Entity;

@Entity
public class TpcCompactDisk extends TpcProduct {
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
