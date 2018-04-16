package edu.mum.cs544.imdb.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
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
	
	private String placeOfBirth;	
	
	@Column(length=2000)
	private String biography;
	
	@Lob
	@NotNull
	private byte[] picture;
	
	@OneToMany(mappedBy="director")
	private List<TVSeries> directedTVSeries = new ArrayList<>();
	
	@OneToMany(mappedBy="artist")
	private Set<EpisodeCharacter> playedCharacters = new HashSet<>();
	
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
	
	public String getBiography() {
		return biography;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EpisodeCharacter> getPlayedCharacters() {
		return playedCharacters;
	}

	public void setPlayedCharacters(Set<EpisodeCharacter> playedCharacters) {
		this.playedCharacters = playedCharacters;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
}
