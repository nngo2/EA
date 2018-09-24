package edu.mum.cs544.l02.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//===========================================================
// NOTE:  This will be stored in different schema
//   It requires the schema "HR" to be created in advance
//============================================================
@Entity(name = "Employee")
@Table(name = "hr.emp")
// @Table(name="emp", schema="hr")
public class Person {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "FIRST")
	private String firstname;
	@Column(name = "LAST", length = 100)
	private String lastname;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street", column = @Column(name = "SHIP_STREET")),
			@AttributeOverride(name = "city", column = @Column(name = "SHIP_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "SHIP_STATE")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "SHIP_ZIP")) })
	private Address shippingAddress;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street", column = @Column(name = "BILL_STREET")),
			@AttributeOverride(name = "city", column = @Column(name = "BILL_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "BILL_STATE")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "BILL_ZIP")) })
	private Address billingAddress;

	public Person() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

}
