package edu.mum.cs544.l01.greeting;

import edu.mum.cs544.l01.model.Person;

public class HelloPerson {
	private Person person;

	HelloPerson() {
	}

	HelloPerson(Person person) {
		this.person = person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}

	public void sayHello() {
		System.out.println("Hello "
				+ person.getFirstname() + " "
				+ person.getLastname());
	}

}
