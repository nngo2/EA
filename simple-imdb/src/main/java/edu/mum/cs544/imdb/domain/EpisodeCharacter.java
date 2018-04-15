package edu.mum.cs544.imdb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EpisodeCharacter {
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@Column(length=2000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person artist;
	
	@ManyToOne
	@JoinColumn(name="episode_id")
	private Episode episode ;	

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

	public Person getArtist() {
		return artist;
	}

	public void setArtist(Person artist) {
		this.artist = artist;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}
}
