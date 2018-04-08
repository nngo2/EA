package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SkillSet")
public class SkillSet extends Resource {
	private double yearOfExperience;
	
	public SkillSet() {
		super();
	}

	public SkillSet(String description, double yearOfExperience) {
		super(description);
		this.yearOfExperience = yearOfExperience;
	}

	public double getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(double yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
}
