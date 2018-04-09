package edu.mum.cs544.volunteerproject.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SkillSet")
public class SkillSet extends Resource {
	private Double yearOfExperience;
	
	public SkillSet() {
		super();
	}

	public SkillSet(String description, Double yearOfExperience) {
		super(description);
		this.yearOfExperience = yearOfExperience;
	}

	public Double getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(Double yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
}
