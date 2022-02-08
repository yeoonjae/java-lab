package hello.springaop;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.springaop.order.OrderRepository;
import hello.springaop.order.OrderService;
import hello.springaop.order.aop.AspectV1;
import hello.springaop.order.aop.AspectV2;
import hello.springaop.order.aop.AspectV3;
import hello.springaop.order.aop.AspectV4Pointcut;
import hello.springaop.order.aop.AspectV5Order;
import hello.springaop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import({AspectV6Advice.class})
public class AopTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void aopInfo() {
    log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
    log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
  }

  @Test
  void success() {
    orderService.orderItem("itemmA");
  }

  @Test
  void exception() {
    assertThatThrownBy(() -> orderService.orderItem("ex"))
        .isInstanceOf(IllegalStateException.class);
  }
}
