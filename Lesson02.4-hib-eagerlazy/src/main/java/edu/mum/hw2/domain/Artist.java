package edu.mum.hw2.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Artist {
	private int artistId;
	private String name;
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public int getArtistId() {
		return artistId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Artist() {
		super();
	}
	public Artist(String name) {
		this.name = name;
	}
}
