package com.spring.springtest.junit;


import static org.assertj.core.api.Assertions.assertThat;

import com.spring.springtest.domain.Study;
import com.spring.springtest.junit.annotation.FastTest;
import com.spring.springtest.junit.annotation.SlowTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class CustomTagingTest {

  @FastTest
  @DisplayName("스터디 만들기 fast")
  void create() {
    Study study = new Study(100);
    assertThat(study.getLimit()).isGreaterThan(0);
    log.info("create 실행");
  }

  @SlowTest
  @DisplayName("스터디 만들기 slow")
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