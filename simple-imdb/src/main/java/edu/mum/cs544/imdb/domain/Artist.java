package edu.mum.cs544.imdb.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("Artist")
public class Artist extends Person {
	private String placeOfBirth;	
	
	@Lob
	@NotNull
	private Byte[] picture;
	
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}	
	
	public Byte[] getPicture() {
		return picture;
	}
	
	public void setPicture(Byte[] picture) {
		this.picture = picture;
	}
}
