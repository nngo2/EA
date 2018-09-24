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
public class M2MBPerson {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@ManyToMany
	  @JoinTable(name="M2MB_PERSON_ADDRESS",
	      joinColumns =  @JoinColumn(name="personId"), 
	       inverseJoinColumns =  @JoinColumn(name="addressId") 
	  )
	private List<M2MBAddress> addressList = new ArrayList<M2MBAddress>();

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

	public List<M2MBAddress> getAddressList() {
		return Collections.unmodifiableList(addressList);
	}

	public void addAddress(M2MBAddress address) {
		addressList.add(address);
		if (!address.getPersonList().contains(this)) {
			address.addPerson(this);
		}
	}

	public void removeAddress(M2MBAddress address) {
		addressList.remove(address);
		if (address.getPersonList().contains(this)) {
			address.removePerson(this);
		}
	}
}
