package hello.springaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

  // hello.springaop.order 패키지와 하위 패키지
  @Pointcut("execution(* hello.springaop.order..*(..))") // pointcut expression
  private void allOrder() {} // pointcut signature (메소드 이름 + 파라미터)

  @Around("allOrder()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
    log.info("[log] {}", joinPoint.getSignature()); // JoinPoint 시그니처
    return joinPoint.proceed();
  }
}
