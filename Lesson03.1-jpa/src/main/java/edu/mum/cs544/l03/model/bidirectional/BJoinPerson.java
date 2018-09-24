package edu.mum.cs544.l03.model.bidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BJoinPerson {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@OneToMany(mappedBy="person")
	private List<BJoinAddress> addressList = new ArrayList<BJoinAddress>();
	
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

	public void addAddress(BJoinAddress address) {
		address.setPerson(this);
		addressList.add(address);
	}

	public void removeAddress(BJoinAddress address) {
		address.setPerson(null);
		addressList.remove(address);
	}

}
