package com.circuit.circuit.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalculatorController {

  private final CalculatorService calculatorService;

  @GetMapping("/calculator")
  public ResponseEntity<String> calculator() {
    String str = calculatorService.cal();
    return ResponseEntity.ok().body("Good! " + str);
  }
}
