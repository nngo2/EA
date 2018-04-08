package edu.mum.cs544.volunteerproject.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Volunteer")
public class Volunteer extends Person{
	private String skillBrief;

	public Volunteer() {
		super();
	}

	public Volunteer(String skillBrief, String firstName, String lastName, Date dob) {
		super(firstName, lastName, dob);
		this.skillBrief = skillBrief;
	}

	public String getSkillBrief() {
		return skillBrief;
	}

	public void setSkillBrief(String skillBrief) {
		this.skillBrief = skillBrief;
	}

}
