package edu.mum.hw2.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Movie {
	@Id @GeneratedValue
	private long id;
	private String name;
	private int rating;
	
	@Lob
	private byte[] cover;

	@ElementCollection
	private List<String> categories = new ArrayList<String>();

	@ElementCollection
	private List<String> comments = new ArrayList<String>();	
	
	@ElementCollection (fetch = FetchType.EAGER)
	private List<Artist> artists = new ArrayList<Artist>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Artist> getArtists() {
		return artists;
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
	public byte[] getCover() {
		return cover;
	}
	public void setCover(byte[] cover) {
		this.cover = cover;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
}
