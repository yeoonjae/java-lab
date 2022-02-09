package hello.springaop.pointcut;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hello.springaop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method helloMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
  }


  @Test
  void printMethod() {
    // helloMethod=public java.lang.String hello.springaop.member.MemberServiceImpl.hello(java.lang.String)
    log.info("helloMethod={}", helloMethod);
  }

  @Test
  void exactMatch() {
    // helloMethod=public java.lang.String hello.springaop.member.MemberServiceImpl.hello(java.lang.String)
    pointcut.setExpression(
        "execution(public String hello.springaop.member.MemberServiceImpl.hello(String))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void allMatch() {
    pointcut.setExpression("execution(* *(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatch() {
    pointcut.setExpression("execution(* hello(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchStar1() {
    pointcut.setExpression("execution(* hel*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchStar2() {
    pointcut.setExpression("execution(* *el*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void nameMatchFalse() {
    pointcut.setExpression("execution(* nono(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  void packageExactMatch1() {
    pointcut.setExpression("execution(* hello.springaop.member.MemberServiceImpl.hello(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactMatch2() {
    pointcut.setExpression("execution(* hello.springaop.member.*.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactFalse() {
    pointcut.setExpression("execution(* hello.springaop.*.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void packageExactMatchSubPackage1() {
    pointcut.setExpression("execution(* hello.springaop.member..*.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // 상상위의 패키지와 매칭한 경우 (..) 사용하여 하위 패키지를 모두 포함하도록 한다. - 성공
  @Test
  void packageExactMatchSubPackage2() {
    pointcut.setExpression("execution(* hello.springaop..*.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // 확장한 클래스 내부에 있는 메소드와 매칭한 경우 - 성공
  @Test
  void typeExactMatch() {
    pointcut.setExpression("execution(* hello.springaop.member.MemberServiceImpl.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

  }

  // 부모 타입의 클래스의 메소드와 매칭한 경우 - 성공
  @Test
  void typeExactMatchSuperType() {
    pointcut.setExpression("execution(* hello.springaop.member.MemberService.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // 자식 클래스의 확장 메소드와 매칭한 경우 - 성공
  @Test
  void typeMatchInternal() throws NoSuchMethodException {
    pointcut.setExpression("execution(* hello.springaop.member.MemberServiceImpl.*(..))");
    Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
  }

  // 부모 타입에 없는 메소드와 매칭한 경우 - 실패
  @Test
  void typeMatchNoSuperTypeMethodFalse() throws NoSuchMethodException {
    pointcut.setExpression("execution(* hello.springaop.member.MemberService.*(..))");
    Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
  }

  // String 타입의 파라미터 허용
  @Test
  void argsMatch() {
    pointcut.setExpression("execution(* *(String))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // 파라미터가 없어야 함
  // () 타입의 파라미터 허용
  @Test
  void argsMatchNoArgs() {
    pointcut.setExpression("execution(* *())");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  // 정확히 하나의 파라미터 허용, 모든 타입 허용
  // (xxx)
  @Test
  void argsMatchStar() {
    pointcut.setExpression("execution(* *(*))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // 숫자와 무관하게 모든 파라미터, 모든 타입 허용
  // (), (xxx), (xxx,xxx)
  @Test
  void argsMatchAll() {
    pointcut.setExpression("execution(* *(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
  // (String), (String, xxx), (String, xxx, xxx)
  @Test
  void argsMatchComplex() {
    pointcut.setExpression("execution(* *(String, ..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }
}
