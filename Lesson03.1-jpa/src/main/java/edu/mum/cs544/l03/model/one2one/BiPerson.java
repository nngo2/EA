package edu.mum.cs544.l03.model.one2one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BiPerson {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private BiAddress address;

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

	public BiAddress getAddress() {
		return address;
	}

	public void setAddress(BiAddress address) {
		this.address = address;
	}

}
