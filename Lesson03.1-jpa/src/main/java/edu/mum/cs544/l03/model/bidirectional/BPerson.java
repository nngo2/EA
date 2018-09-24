package edu.mum.cs544.l03.model.bidirectional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BPerson {
	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	@OneToMany(mappedBy="person")
	private List<BAddress> addressList = new ArrayList<BAddress>();

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

	public List<BAddress> getAddressList() {
		return Collections.unmodifiableList(addressList);
	}

	public void addAddress(BAddress address) {
		address.setPerson(this);
		addressList.add(address);
	}

	public void removeAddress(BAddress address) {
		address.setPerson(null);
		addressList.remove(address);
	}
}
