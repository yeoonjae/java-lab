package hello.springaop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

  /**
   * 구조 변경
   **/

  private final InternalService internalService;

  public void external() {
    log.info("call external");
    internalService.internal(); // 외부 메소드 호출
  }

}
