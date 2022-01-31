package hello.proxy.app.v1;

import org.springframework.scheduling.annotation.Scheduled;

public class OrderControllerV1Impl implements OrderControllerV1{

  private final OrderServiceV1 orderService;

  public OrderControllerV1Impl(OrderServiceV1 orderService) {
    this.orderService = orderService;
  }

  @Override
  @Scheduled
  public String request(String itemId) {
    orderService.orderItem(itemId);
    return "ok";
  }

  @Override
  public String noLog() {
    return "ok";
  }
}
