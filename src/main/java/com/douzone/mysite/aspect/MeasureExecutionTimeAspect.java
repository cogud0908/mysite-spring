package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {

	// 모든 return값 repository에 있는 모든 클래스, 모든 메소드 접근
	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		// before
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// method 실행
		Object result = pjp.proceed();
		
		// after
		stopWatch.stop();
		Long totalTime = stopWatch.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[ExcutionTime]["+taskName+"] "+ totalTime+"milliseconds");
		
		return result;
	}
}
