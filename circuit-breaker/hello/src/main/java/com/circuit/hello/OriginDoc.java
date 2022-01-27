package com.circuit.hello;

import static org.hamcrest.MatcherAssert.assertThat;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class OriginDoc {

  public static void main(String[] args) {
    // Create a custom configuration for a CircuitBreaker
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
        .failureRateThreshold(50)
        .waitDurationInOpenState(Duration.ofMillis(1000))
        .permittedNumberOfCallsInHalfOpenState(2)
        .slidingWindowSize(2)
        .recordExceptions(IOException.class, TimeoutException.class)
        .build();

    // Create a CircuitBreakerRegistry with a custom global configuration
    CircuitBreakerRegistry circuitBreakerRegistry =
        CircuitBreakerRegistry.of(circuitBreakerConfig);

    CircuitBreaker circuitBreaker = circuitBreakerRegistry
        .circuitBreaker("name");

    Supplier<String> decoratedSupplier = CircuitBreaker
        .decorateSupplier(circuitBreaker, OriginDoc::doSomething);

    String result = Try.ofSupplier(decoratedSupplier)
        .recover(throwable -> "Hello from Recovery").get();
  }



  public static String doSomething() {
    int number = new Random().nextInt(10);
    if (number > 5) {
      try {
        throw new TimeoutException("Fail !!!!!! ");
      } catch (TimeoutException e) {
        e.printStackTrace();
      }
    }
    return "Success !!!!! ";
  }

}
