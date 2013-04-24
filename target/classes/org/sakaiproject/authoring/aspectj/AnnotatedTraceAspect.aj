package org.sakaiproject.authoring.aspectj;

import java.util.logging.*;

import org.aspectj.lang.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AnnotatedTraceAspect {
	private Logger _logger = Logger.getLogger("trace");

	AnnotatedTraceAspect() {
		_logger.setLevel(Level.ALL);
	}

	@Pointcut("(execution(* *.*(..))) && !within(*Aspect)")
	protected void traceMethods() {
	}

	// TODO: porque não aceita o thisJointPoint ?! (aparentemente só o around
	// aceita.
	// @Before("traceMethods()")
	// public void doBefore(final ProceedingJoinPoint thisJoinPoint) {
	// if (_logger.isLoggable(Level.INFO)) {
	// Signature sig = thisJoinPoint.getStaticPart().getSignature();
	// _logger.logp(Level.INFO,
	// sig.getDeclaringType().getName(),
	// sig.getName(),
	// "Entering");
	//            
	// // Alternativa:
	// // String joinPointName =
	// thisJoinPoint.getThis().getClass().getSimpleName() + "." +
	// thisJoinPoint.getSignature().getName() + "()"
	// }
	// }

//	@Around("traceMethods()")
//	public Object doThing(final ProceedingJoinPoint joinPoint) throws Throwable {		
//		final String joinPointName = joinPoint.getThis().getClass()
//				.getSimpleName()
//				+ "." + joinPoint.getSignature().getName() + "()";
//		long startTime = System.currentTimeMillis();
//
//		if (_logger.isLoggable(Level.INFO)) {
//			_logger.log(Level.INFO, "Entering [" + joinPointName + "]");
//		}
//		Object retValue = joinPoint.proceed();
//		if (_logger.isLoggable(Level.INFO)) {
//			long endTime = System.currentTimeMillis() - startTime;
//			_logger.log(Level.INFO, "Leaving  [" + joinPointName + "]"
//					+ endTime + " ms");
//		}
//
//		return retValue;
//	}

}