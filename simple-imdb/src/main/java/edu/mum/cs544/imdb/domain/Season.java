package edu.mum.cs544.imdb.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Range;

@Entity
public class Season {
	@Id @GeneratedValue
	private int id;
	
	@Range(min = 1990, max = 2018)
	private int year;
	
	private String summary;
	
	@Lob
	private Byte[] poster;
	
	@ManyToOne
	@JoinColumn(name = "tvseries_id")
	private TVSeries tvSeries;
	
	@OneToMany(mappedBy="season", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Episode> episodes;
	
	public List<Episode> getEpisodes() {
		return Collections.unmodifiableList(episodes);
	}
	
	public void addEpisode(Episode episode) {
		episode.setSeason(this);
		episodes.add(episode);
	}
	
	public void removeEpisode(Episode episode) {
		episode.setSeason(null);
		episodes.remove(episode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Byte[] getPoster() {
		return poster;
	}

	public void setPoster(Byte[] poster) {
		this.poster = poster;
	}

	public TVSeries getTvSeries() {
		return tvSeries;
	}

	public void setTvSeries(TVSeries tvSeries) {
		this.tvSeries = tvSeries;
	}
}
