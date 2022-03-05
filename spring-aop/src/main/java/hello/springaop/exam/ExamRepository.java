package hello.springaop.exam;

import hello.springaop.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ExamRepository {

  private static int seq = 0;

  /**
   * 5번에 1번 실패하는 요청청
   */
  @Trace
  public String save(String itemId) {
    seq++;
    if (seq % 5 == 0) {
      throw new IllegalStateException("예외 발생");
    }
    return "OK";
  }
}
