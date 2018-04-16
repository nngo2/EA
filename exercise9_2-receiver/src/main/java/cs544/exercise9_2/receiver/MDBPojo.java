package cs544.exercise9_2.receiver;

import javax.jms.*;

import cs544.exercise9_2.sender.Calculator;
//import cs544.exercise9_2.sender.Person;

public class MDBPojo implements MessageListener {

    public synchronized void onMessage(Message message){
    	ObjectMessage objmessage = (ObjectMessage)message;
    	try {
			//Person person = (Person)objmessage.getObject();
			//System.out.println("MDBPojo receives message with person object : " + person.getFirstName()+" "+person.getLastName());
    		
    		Calculator calc = (Calculator)objmessage.getObject();
    		
    		switch(calc.getOperator()) {
    			case PLUS: 
    				System.out.println(String.format("%s + %s = %s", calc.getNum1(), calc.getNum2(), calc.getNum1() + calc.getNum2()));
    				break;
    			case MINUS: 
    				System.out.println(String.format("%s - %s = %s", calc.getNum1(), calc.getNum2(), calc.getNum1() - calc.getNum2()));  
    				break;    				
    			case MULTIPLE: 
    				System.out.println(String.format("%s * %s = %s", calc.getNum1(), calc.getNum2(), calc.getNum1() * calc.getNum2()));   	
    				break;    				
    			case DIVIDE: 
    				System.out.println(String.format("%s / %s = %s", calc.getNum1(), calc.getNum2(), calc.getNum1() / calc.getNum2()));    		
    				break;    				
    			default:	
    				System.out.println("Unsupport operator");
    		}
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
}

