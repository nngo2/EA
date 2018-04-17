package cs544.exercise10_2.service;

public class GreetingService implements IGreeting {
	private String greeting;

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getMessage(Person person) {
		return greeting + " " + person.getFirstName() + " "
				+ person.getLastName();
	}
}