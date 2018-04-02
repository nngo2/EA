package edu.mum.cs544.l01;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.cs544.l01.greeting.HelloPerson;

public class MainApp {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("application-context.xml")) {
	
			HelloPerson hp = (HelloPerson) context.getBean("helloWorld");
			hp.sayHello();
		}catch (Exception e) {
			System.out.print("There was a problem initializing the application.");
			e.printStackTrace();
		}
	}
}
