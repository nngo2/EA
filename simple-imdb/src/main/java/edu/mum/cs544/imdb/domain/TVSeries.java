package edu.mum.cs544.imdb.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TVSeries {
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@Column(length=2000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="studio_id")
	private Studio studio;
	
	@ManyToOne
	@JoinColumn(name="director_id")
	private Person director;
	
	@ManyToMany(mappedBy="joinedTVSeries")
	private List<Person> casts = new ArrayList<>();
	
	@OneToMany(mappedBy="tvSeries", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Season> seasons = new ArrayList<Season>();
	
	public int seasonCount() {
		return seasons.size();
	}
	
	public int episodeCount() {
		int total = 0;
		for (Season s : seasons) {
			total += s.episodeCount(); 
		}
		return total;
	}
	
	public List<Season> getSeasons() {
		return Collections.unmodifiableList(seasons);
	}
	
	public void addSeason(Season season) {
		season.setTvSeries(this);
		seasons.add(season);
	}	
	
	public void removeSeason(Season season) {
		season.setTvSeries(null);
		seasons.remove(season);
	}	
	
	public List<Person> getCasts() {
		return Collections.unmodifiableList(casts);
	}
			
	public void addCast(Person artist) {
		casts.add(artist);
		if (!artist.getJoinedTVSeries().contains(this)) {
			artist.addJoinedTVSeries(this);
		}
	}
	
	public void removeCast(Person artist) {
		casts.remove(artist);
		if (artist.getJoinedTVSeries().contains(this)) {
			artist.removeJoinedTVSeries(this);
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

	public Studio getStudio() {
		return studio;
	}

	public void setStudio(Studio studio) {
		this.studio = studio;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
