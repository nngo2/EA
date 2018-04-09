package cs544.exercise5_3.bank.jms;

import org.springframework.stereotype.Service;

@Service
public class JMSSender implements IJMSSender{

	public void sendJMSMessage (String text){
		System.out.println("JMSSender: sending JMS message ="+text);
	}

}
