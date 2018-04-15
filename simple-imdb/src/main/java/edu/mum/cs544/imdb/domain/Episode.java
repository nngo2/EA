package edu.mum.cs544.imdb.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Episode {
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@Column(length=2000)
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	
	@ManyToMany(mappedBy="joinedEpisodes")
	private List<Person> casts = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="season_id")
	private Season season;
	
	public List<Person> getCasts() {
		return Collections.unmodifiableList(casts);
	}
	
	public void addCast(Person artist) {
		casts.add(artist);
		if (!artist.getJoinedEpisodes().contains(this)) {
			artist.addJoinedEpisode(this);
		}
	}
	
	public void removeCast(Person artist) {
		casts.remove(artist);
		if (artist.getJoinedEpisodes().contains(this)) {
			artist.removeJoinedEpisode(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public Season getSeason() {
		return season;
	}
	
	public void setSeason(Season season) {
		this.season = season;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
