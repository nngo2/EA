package edu.mum.cs544.l02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//===========================================================
// Person values can not be modified
//============================================================

@Entity
public class Person {

	@Id
	@GeneratedValue
	private int id;

	private String firstname;
	private String lastname;

	Person() { 
		// default constructor is required by Hibernate 
		// It is set to private so it is NOT ACCESSIBLE outside the package		
	}
	
	public Person(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getId() {
		return id;
	}

}
