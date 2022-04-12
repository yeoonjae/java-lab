package hello.springaop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

  // private final ApplicationContext applicationContext;
  // ObjectProvider : 스프링 컨테이너에서 조회하는 것을 스프링 빈 시점이 아니라 `실제 객체를 사용하는 시점`으로 지연할 수 있다. 따라서 이는 지연해결 방법이다.
  private final ObjectProvider<CallServiceV2> callServiceProvider;

  public CallServiceV2(ObjectProvider<CallServiceV2> objectProvider) {
    this.callServiceProvider = objectProvider;
  }

  public void external() {
    log.info("call external");
    // CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
    // getObject() : 싱글톤이면 스프링 컨테이너안에서 꺼내온다. 자기 자신을 주입받는 것이 아니기 때문에 순환참조가 발생하지 않는다.
    CallServiceV2 callServiceV2 = callServiceProvider.getObject();

    callServiceV2.internal(); // 외부 메소드 호출
    
  }

  public void internal() {
    log.info("call internal");
  }
}
