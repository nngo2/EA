package edu.mum.cs544.imdb.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="person_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Person {
	@Id @GeneratedValue
	private int id;
	
	@NotNull
	private String name;
	
	@Lob
	private Character[] biography;
	
	@OneToMany(mappedBy="director")
	private List<TVSeries> directedTVSeries;
	
	@ManyToMany
	@JoinTable(name="person_artist_series", 
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="tvseries_id"))
	private List<TVSeries> joinedTVSeries;
	
	@ManyToMany
	@JoinTable(name="person_artist_episode", 
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="espisode_id"))
	private List<Episode> joinedEpisodes;
	
	public List<Episode> getJoinedEpisodes() {
		return Collections.unmodifiableList(joinedEpisodes);
	}
	
	public void addJoinedEpisode(Episode episode) {
		joinedEpisodes.add(episode);
		if (!episode.getCasts().contains(this)) {
			episode.addCast(this);
		}
	}
	
	public void removeJoinedEpisode(Episode episode) {
		joinedEpisodes.remove(episode);
		if (episode.getCasts().contains(this)) {
			episode.removeCast(this);
		}
	}
	
	public List<TVSeries> getJoinedTVSeries() {
		return Collections.unmodifiableList(joinedTVSeries);
	}
	
	public void addJoinedTVSeries(TVSeries tvSeries) {
		joinedTVSeries.add(tvSeries);
		if (!tvSeries.getCasts().contains(this)) {
			tvSeries.addCast(this);
		}
	}
	
	public void removeJoinedTVSeries(TVSeries tvSeries) {
		joinedTVSeries.remove(tvSeries);
		if (tvSeries.getCasts().contains(this)) {
			tvSeries.removeCast(this);
		}
	}
	
	public List<TVSeries> getDirectedTVSeries() {
		return Collections.unmodifiableList(directedTVSeries);
	}

	public void addDirectedTVSeries(TVSeries tvSeries) {
		tvSeries.setDirector(this);
		directedTVSeries.add(tvSeries);
	}

	public void removeDirectedTVSeries(TVSeries tvSeries) {
		tvSeries.setDirector(null);
		directedTVSeries.remove(tvSeries);
	}
	
	public Character[] getBiography() {
		return biography;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setBiography(Character[] biography) {
		this.biography = biography;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
