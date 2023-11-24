package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAop {

  @Pointcut("execution(* com.example.demo.*Service.*(..))")
  public void pointcut(){}


  @Before(value = "pointcut()")
  public void before(JoinPoint point) {
    String className = point.getTarget().getClass().getSimpleName();
    String methodName = point.getSignature().getName();
    log.warn("{}#{} 開始執行", className, methodName);
  }

  @AfterReturning(value = "pointcut()", returning = "result")
  public void afterReturn(JoinPoint point, Object result) {
    String className = point.getTarget().getClass().getSimpleName();
    String methodName = point.getSignature().getName();
    if (result != null) {
      log.warn("{}#{} 執行結果: {}", className, methodName, result);
    } else {
      log.warn("{}#{} 執行結果: null", className, methodName);
    }
  }

  // 例外通知，目標方法出現exception時執行，可以指定在出現特定例外時才執行
  // 如果把參數設成NullPointException則只在出現此例外時才執行
  @AfterThrowing(value = "pointcut()", throwing = "e")
  public void afterThrow(JoinPoint point, Exception e) {
    String className = point.getTarget().getClass().getSimpleName();
    String methodName = point.getSignature().getName();
    log.warn("{}#{} 執行失敗", className, methodName, e);
  }

}
