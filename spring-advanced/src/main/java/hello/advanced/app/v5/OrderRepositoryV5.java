package hello.advanced.app.v5;


import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

  private final LogTrace trace;
  private final TraceTemplate traceTemplate;

  public OrderRepositoryV5(LogTrace trace) {
    this.trace = trace;
    this.traceTemplate = new TraceTemplate(trace);
  }

  public void save(String itemId) {

    traceTemplate.execute("OrderRepository.save()", () -> {
      // 저장 로직
      if (itemId.equals("ex")) {
        throw new IllegalArgumentException("예외 발생");
      }
      sleep(1000);
      return null;
    });
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
