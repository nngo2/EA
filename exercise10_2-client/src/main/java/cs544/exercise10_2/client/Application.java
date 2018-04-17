package cs544.exercise10_2.client;

import cs544.exercise10_2.service.ICalculator;
import cs544.exercise10_2.service.IGreeting;
import cs544.exercise10_2.service.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class Application {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		IGreeting remoteService = context.getBean("greetingHttpInvokerProxy", IGreeting.class);

		Person person = new Person("John", "Doe");
		String result = remoteService.getMessage(person);
		System.out.println("Receiving result: " + result);
	
		ICalculator calcService = context.getBean("calculatorHttpInvokerProxy", ICalculator.class);
		calc(calcService, '+', 10);
		calc(calcService, '/', 2);
		calc(calcService, '*', 5);
		calc(calcService, '-', 3);
	}
	
	private static void calc(ICalculator calcService, char operator, int value) {
		StopWatch s = new StopWatch();
		s.start();
		int result = calcService.calc(operator, value);
		s.stop();
		System.out.println("State= " + result);
		System.out.println(s.prettyPrint());
	}
}
