package edu.mum.cs544.imdb.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TVSeries {
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@ElementCollection(targetClass=Genre.class)
	@Enumerated(EnumType.STRING)
	private List<Genre> genres;
	
	@Column(length=2000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="studio_id")
	private Studio studio;
	
	@ManyToOne
	@JoinColumn(name="director_id")
	private Person director;
	
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
	
	public List<EpisodeCharacter> getCasts() {
		List<EpisodeCharacter> casts = seasons.stream()
				.flatMap(s -> s.getEpisodes().stream())
				.flatMap(e -> e.getCharacters().stream())
				.collect(Collectors.toList());
		return Collections.unmodifiableList(casts);
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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	} 
}
