package edu.mum.cs544.imdb.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;

@Entity
public class Episode {
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@Column(length=2000)
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date arrivalDate;
	
	@Range(min=0, max=10)
	private int rating = 0;
	
	@OneToMany(mappedBy="episode", cascade = {CascadeType.REMOVE})
	private Set<EpisodeCharacter> characters = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="season_id")
	private Season season;
	
	@OneToMany(mappedBy="episode", cascade = {CascadeType.REMOVE})
	private List<UserComment> comments;

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

	public Set<EpisodeCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<EpisodeCharacter> characters) {
		this.characters = characters;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<UserComment> getComments() {
		return comments;
	}

	public void setComments(List<UserComment> comments) {
		this.comments = comments;
	}

}
