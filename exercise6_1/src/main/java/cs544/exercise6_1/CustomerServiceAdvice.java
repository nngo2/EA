package cs544.exercise6_1;

import java.util.Date;

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
public class CustomerServiceAdvice {
	@Autowired
	private ILogger logger;
	
	public ILogger getLogger() {
		return logger;
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
//	@After("execution(* cs544.exercise6_1.CustomerDAO.save(..))")
//	public void logSave(JoinPoint jointPoint) {
//		Object[] args = jointPoint.getArgs();
//		if (args != null && args.length > 0) {
//			Customer c = (Customer) args[0];
//			logger.log(String.format("CustomerDAO: saving customer %s", c.getName()));
//		}
//		
//	}
	
	@Around("execution(* cs544..CustomerDAO.save(..))")
	public Object invoke(ProceedingJoinPoint call) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(call.getSignature().getName());
		Object retVal = call.proceed();
		sw.stop();
		long totaltime = sw.getLastTaskTimeMillis();
		System.out.println("Time to execute save = " + totaltime);
		return retVal;
	}
	
	@After("execution(* cs544..EmailSender.sendEmail(..))")
	public void logSave(JoinPoint jointPoint) {
		Object[] args = jointPoint.getArgs();
		IEmailSender sender = (IEmailSender) jointPoint.getTarget();
		if (args != null && args.length >= 2) {
			System.out.println(String.format("%s, method= %s, address= %s, message= %s, outgoing mail server= %s", 
					new Date().toString(), 
					jointPoint.getSignature().getName(), 
					args[0], args[1], sender.getOutgoingMailServer()));	
		}	
	}
}
