package hello.springaop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

  // hello.springaop.order 패키지와 하위 패키지
  @Pointcut("execution(* hello.springaop.order..*(..))") // pointcut expression
  public void allOrder() {} // pointcut signature (메소드 이름 + 파라미터)

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  public void allService() {}

  // allOrder && allService
  @Pointcut("allOrder() && allService()")
  public void orderAndService() {}


}
