package edu.mum.cs544.l03.model.many2many;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class M2MUniPerson {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@ManyToMany
	  @JoinTable(name="M2MU_PERSON_ADDRESS",
	      joinColumns =  @JoinColumn(name="personId"), 
	       inverseJoinColumns =  @JoinColumn(name="addressId") 
	  )
	private List<M2MUniAddress> addressList = new ArrayList<M2MUniAddress>();

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

	public List<M2MUniAddress> getAddressList() {
		return Collections.unmodifiableList(addressList);
	}

	public void addAddress(M2MUniAddress address) {
		addressList.add(address);
	}

	public void removeAddress(M2MBAddress address) {
		addressList.remove(address);
	}
}
