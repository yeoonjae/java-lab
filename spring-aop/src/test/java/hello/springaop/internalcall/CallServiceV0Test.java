package hello.springaop.internalcall;

import hello.springaop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
@Slf4j
class CallServiceV0Test {

  @Autowired
  CallServiceV0 callServiceV0;

  @Test
  void external() {
    log.info("target={}", callServiceV0.getClass());
    callServiceV0.external();

  }

  @Test
  void internal() {
    callServiceV0.internal();
  }
}