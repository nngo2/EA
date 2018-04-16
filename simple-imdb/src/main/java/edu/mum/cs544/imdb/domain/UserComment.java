package edu.mum.cs544.imdb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserComment {
	@Id @GeneratedValue
	private int id;
	
	@Column(length=2000) 
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private ImdbUser user;
	
	@ManyToOne
	@JoinColumn(name="episode_id")
	private Episode episode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public ImdbUser getUser() {
		return user;
	}

	public void setUser(ImdbUser user) {
		this.user = user;
	}
	
}
