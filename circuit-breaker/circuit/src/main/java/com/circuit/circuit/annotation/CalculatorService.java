package com.circuit.circuit.annotation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

  @CircuitBreaker(name="customCircuitBreaker",fallbackMethod = "fallback")
  public String cal() {
    Random r = new Random();
    int number = r.nextInt(10);

    if (number % 2 == 0) {
      throw new RuntimeException("Fail !!");
    }
    return "Success";
  }

  private String fallback(Exception e) {
    return "Fallback Method Running and Error is "+e.getMessage();
  }
}
