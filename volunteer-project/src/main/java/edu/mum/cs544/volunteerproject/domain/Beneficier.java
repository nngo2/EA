package edu.mum.cs544.volunteerproject.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("Beneficer")
public class Beneficier extends Person {
	
	@Lob
	private Character[] description;
	
	public Beneficier() {
		super();
	}

	public Beneficier(String firstName, String lastName, Date dob) {
		super(firstName, lastName, dob);
	}
	
	public Character[] getDescription() {
		return description;
	}

	public void setDescription(Character[] description) {
		this.description = description;
	}
}
