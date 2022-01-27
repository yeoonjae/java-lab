package com.circuit.hello;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class HelloController {

  @PostMapping("/hello")
  public Boolean hello(@RequestBody String name) {
    if (name.equals("ex")) {
      log.info("RuntimeException Error!!!");
      return false;
    } else if (name.equals("yeonjae")) {
      return true;
    }
    return false;
  }
}
