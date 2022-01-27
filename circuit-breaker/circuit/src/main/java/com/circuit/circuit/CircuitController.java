package com.circuit.circuit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitController {

  private RestTemplateBuilder builder;

  @PostMapping("/circuit/check")
  @ResponseBody
  @CircuitBreaker(name = "circuitBreaker", fallbackMethod = "fallback")
  public String circuitCheck(@RequestBody UserInfo name) {
    RestTemplate restTemplate = builder.build();
    ResponseEntity<Boolean> b = restTemplate.exchange(new RequestEntity<>(name,HttpMethod.POST,
        URI.create("http://localhost:8081/hello")), Boolean.class);

    log.info("ResponseBody={}", b.getBody());

    if (b.getBody()) {
      log.info("Good");
      return "Circuit check is Good";
    }
    return " Fail :: Hello Check";
  }

  private String fallback(@PathVariable String name, Throwable e) {
    log.info("fallback fallback , Throws ={}",e.getMessage());
    return "fallback Method Running";
  }
}
