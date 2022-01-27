package com.circuit.hello;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Resilience4jMain implements QuoteService {

  private String productName;

  public static void main(String[] args) {

    List<String> products = List.of("Soap", "Tooth Paste", "Biscuit", "Pepsi", "Tea", "Coke");

    Resilience4jMain resQuoteService = new Resilience4jMain();

    // Circuit breaker Configuration
    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
        .slidingWindow(10, 5, SlidingWindowType.COUNT_BASED)
        .failureRateThreshold(50)
        .permittedNumberOfCallsInHalfOpenState(3)
        .waitDurationInOpenState(Duration.ofSeconds(10))
        .build();

    // Circuit breaker Registry
    CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);

    // Circuit breaker
    CircuitBreaker circuitBreaker = registry.circuitBreaker("myCustomCircuitBreaker");

    // Supplier
    Supplier<Integer> supplier = CircuitBreaker.decorateSupplier(circuitBreaker,
        resQuoteService::getQuote);

    // Call the method
    for (String product : products) {
      resQuoteService.setProductName(product);
      Try.ofSupplier(supplier).recover(resQuoteService::getQuoteFallback);
    }

  }


  @Override
  public int getQuote() {
    log.info("Inside Quote Method for product "+ this.getProductName());
    if (getProductName().equalsIgnoreCase("Soap")) {
      return new Random().nextInt(100);
    } else if (getProductName().equalsIgnoreCase("Tooth Paste")) {
      return new Random().nextInt(100);
    } else {
      throw new RuntimeException("Product Not Available");
    }
  }

  public int getQuoteFallback(Throwable t) {
    log.info("Inside Quote fallback Method for product "+ this.getProductName());
    if (getProductName().equalsIgnoreCase("Soap")) {
      return 10;
    } else if (getProductName().equalsIgnoreCase("Tooth Paste")) {
      return 20;
    }
    return 0;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }
}
