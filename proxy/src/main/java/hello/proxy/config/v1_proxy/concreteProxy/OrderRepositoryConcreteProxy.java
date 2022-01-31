package hello.proxy.config.v1_proxy.concreteProxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

  private final LogTrace trace;
  private final OrderRepositoryV2 target;

  @Override
  public void save(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRespository.save()");
      target.save(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
