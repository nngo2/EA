package edu.mum.cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.mum.cs544.greeting.HelloPerson;

@SpringBootApplication
public class Ex13SpringBootApplication implements CommandLineRunner {
	@Autowired
	private HelloPerson greeting;
	
	public static void main(String[] args) {
		SpringApplication.run(Ex13SpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		greeting.sayHello();
	}
}
