package hello.proxy.config.v1_proxy.concreteProxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

public class OrderServiceConcreteProxy extends OrderServiceV2 {

  private final OrderServiceV2 target;
  private final LogTrace trace;

  public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace trace) {
    super(null);
    this.target = target;
    this.trace = trace;
  }

}
