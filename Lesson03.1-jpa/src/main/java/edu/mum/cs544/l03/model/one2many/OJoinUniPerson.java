package edu.mum.cs544.l03.model.one2many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class OJoinUniPerson {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@OneToMany
	@JoinTable(name = "OJ_PERSON_ADDRESS", 
		joinColumns = @JoinColumn(name = "PersonId"), 
		inverseJoinColumns = @JoinColumn(name = "addressId"))
	private List<OJoinUniAddress> address = new ArrayList<OJoinUniAddress>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<OJoinUniAddress> getAddress() {
		return address;
	}

	public void setAddress(List<OJoinUniAddress> address) {
		this.address = address;
	}

}
