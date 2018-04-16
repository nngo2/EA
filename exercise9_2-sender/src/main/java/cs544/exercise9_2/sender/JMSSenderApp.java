package cs544.exercise9_2.sender;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMSSenderApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfigsender.xml");
		JMSSender jmssender = context.getBean("jmsSender", JMSSender.class);
		
//		Person person = new Person("John", "Doe");
//		jmssender.send(person);
		
		Calculator c1 = new Calculator(1, 2.5, Operator.PLUS);
		jmssender.send(c1);
		
		c1 = new Calculator(4.5, 2.5, Operator.MINUS);
		jmssender.send(c1);
		
		c1 = new Calculator(4, 2.5, Operator.MULTIPLE);
		jmssender.send(c1);
		
		c1 = new Calculator(4, 1.5, Operator.DIVIDE);
		jmssender.send(c1);
		
		context.close();
	}
}
