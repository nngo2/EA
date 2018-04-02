package edu.mum.cs544.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.cs544.model.Person;

@Component
public class HelloPerson {
	@Autowired
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
