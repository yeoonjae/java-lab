package com.circuit.circuit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneModuleTest {

  private final HelloService helloService;

  public OneModuleTest(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping("/hello")
  public String hello() {
    return helloService.greeting();
  }
}
