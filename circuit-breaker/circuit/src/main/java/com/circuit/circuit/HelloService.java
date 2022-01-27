package com.circuit.circuit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  @CircuitBreaker(name = "hello", fallbackMethod = "helloFallback")
  public String greeting() {
    randomException();
    return "hello world!";
  }

  private void randomException() {
    int randomInt = new Random().nextInt(10);

    if(randomInt <= 7) {
      throw new RuntimeException("failed");
    }
  }

  private String helloFallback(Throwable t) {
    return "fallback invoked! exception type : " + t.getClass();
  }
}
