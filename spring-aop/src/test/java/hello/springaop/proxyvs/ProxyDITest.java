package hello.springaop.proxyvs;

import hello.springaop.member.MemberService;
import hello.springaop.member.MemberServiceImpl;
import hello.springaop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK 동적 프록시, DI 예외
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB 프록시, 성공
@Import(ProxyDIAspect.class)
public class ProxyDITest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberServiceImpl memberServiceImpl;

  @Test
  void go() {
    log.info("memberService class={}", memberService.getClass());
    log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
    memberServiceImpl.hello("hello");

  }

}
