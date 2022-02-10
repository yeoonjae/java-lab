package com.spring.springtest.junit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.spring.springtest.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @DisplayNameGeneration
 * - Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정.
 * - 기본 구현체로 ReplaceUnderscores 제공
 * @DisplayName
 * - 어떤 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션.
 * - @DisplayNameGeneration 보다 우선 순위가 높다.
 */
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // underscore를 공백으로 바꾸어줌
class NamingTest {

  @Test
  @DisplayName("스터디 만들기")
  void create_new_test() {
    Study study = new Study();
    assertNotNull(study);
    log.info("create 실행");
  }

  @Test
  void create_new_study_again() {
    log.info("create1 실행");
  }

  @Test
  @Disabled
  void create_disabled_test() {
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