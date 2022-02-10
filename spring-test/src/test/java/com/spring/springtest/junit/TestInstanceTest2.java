package com.spring.springtest.junit;


import com.spring.springtest.junit.annotation.FastTest;
import com.spring.springtest.junit.annotation.SlowTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class TestInstanceTest2 {

  int value = 1;

  @BeforeAll
  static void beforeAll() {
    log.info("beforeAll 실행");
  }

  @AfterAll
  static void afterAll() {
    log.info("afterAll 실행");
  }

  @Order(2)
  @FastTest
  @DisplayName("스터디 만들기 fast")
  void create() {
    log.info("create 실행, value={}", value++);
    log.info("thisInstance={}", this);
  }

  @Order(1)
  @SlowTest
  @DisplayName("스터디 만들기 slow")
  void create1() {
    log.info("create1 실행, value={}", value++);
    log.info("thisInstance={}", this);
  }

}