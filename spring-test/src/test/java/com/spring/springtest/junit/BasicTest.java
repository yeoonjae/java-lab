package com.spring.springtest.junit;

import static org.junit.jupiter.api.Assertions.*;

import com.spring.springtest.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Slf4j
class BasicTest {

  @Test
  void create() {
    Study study = new Study();
    assertNotNull(study);
    log.info("create 실행");
  }

  @Test
  void create1() {
    log.info("create1 실행");
  }

  @Test
  @Disabled
  void create2Disabled() {
    log.info("beforeAfterTest 실행");
  }

  @BeforeAll
  static void beforeAll() {
    log.info("beforeAll 실행");
  }

  @AfterAll
  static void afterAll() {
    log.info("afterAll 실행");
  }

  @BeforeEach
  void beforeEach() {
    log.info("beforeEach 실행");
  }

  @AfterEach
  void afterEach() {
    log.info("afterEach 실행");
  }
}