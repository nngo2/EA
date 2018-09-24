package edu.mum.cs544.l03.model.many2one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MUniAddress {
	@Id
	@GeneratedValue
	private int id;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	 @ManyToOne
	  @JoinColumn(name="personId")
	 private MUniPerson person;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public MUniPerson getPerson() {
		return person;
	}
	public void setPerson(MUniPerson mUniPerson) {
		this.person = mUniPerson;
	}
	
}
