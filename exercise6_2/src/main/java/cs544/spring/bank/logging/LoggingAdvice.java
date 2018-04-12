package cs544.spring.bank.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAdvice {
	@Autowired
	private ILogger logger;

	@After("execution(* *..bank.dao.*.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		logger.log("Called method in bank.dao: method =" + joinPoint.getSignature().getName());
	}
	
	@Around("execution(* *..bank.service.*.*(..))")
	public Object invoke(ProceedingJoinPoint call) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(call.getSignature().getName());
		Object retVal = call.proceed();
		sw.stop();
		long totaltime = sw.getLastTaskTimeMillis();
		logger.log(String.format("Time to execute method = %s, time = %s", call.getSignature().getName(), totaltime));
		return retVal;
	}
	
//	@After("execution(* *..JMSSender.sendJMSMessage(..))")
//	public void afterJMSMessageSent(JoinPoint joinPoint) {
//		logger.log("JMS Message sent = " + joinPoint.getArgs()[0]);
//	}
	
	@After("execution(* *..JMSSender.sendJMSMessage(String)) && args(text)")
	public void afterJMSMessageSent(JoinPoint joinPoint, String text) {
		logger.log("JMS Message sent = " + text);
	}	

}
