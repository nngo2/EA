package edu.mum.cs544.l03.model.many2one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class MJoinUniAddress {
	@Id
	@GeneratedValue
	private int id;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	@ManyToOne
	  @JoinTable(name="MJ_ADDRESS_PERSON")
	private MJoinUniPerson person;
	
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
	public MJoinUniPerson getPerson() {
		return person;
	}
	public void setPerson(MJoinUniPerson person) {
		this.person = person;
	}
	
}
