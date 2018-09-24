package edu.mum.cs544.l02.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

//===========================================================
// NOTE:  This will be stored in different schema
//    It requires the schema "HR" to be created in advance
//============================================================
@Entity
@Table(name="hr.drivers")
//@Table(name="drivers", schema="hr")
public class Driver {
	@Id
	private int id;
	
	private String name;
	private String license;
	
	@Enumerated(EnumType.STRING)
	private Rating rating;

	@Embedded
    private Address homeAddress;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
}
