package edu.mum.cs544.l02.model.joined;

import javax.persistence.Entity;

@Entity
public class JtCompactDisk extends JtProduct {
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
