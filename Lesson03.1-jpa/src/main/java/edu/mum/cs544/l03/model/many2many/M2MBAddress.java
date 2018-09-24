package edu.mum.cs544.l03.model.many2many;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class M2MBAddress {
	@Id
	@GeneratedValue
	private int id;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	 @ManyToMany(mappedBy="addressList")
	 private List<M2MBPerson> personList = new ArrayList<M2MBPerson>();
	
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

	public List<M2MBPerson> getPersonList() {
		return Collections.unmodifiableList(personList);
	}
	
	public void addPerson(M2MBPerson person) {
		personList.add(person);
		if (!person.getAddressList().contains(this)) {
			person.addAddress(this);
		}
	}

	public void removePerson(M2MBPerson person) {
		personList.remove(person);
		if (!person.getAddressList().contains(this)) {
			person.removeAddress(this);
		}
	}

	
}
