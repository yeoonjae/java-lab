package com.spring.springtest.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spring.springtest.domain.Study;
import com.spring.springtest.study.StudyStatus;
import java.time.Duration;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // underscore를 공백으로 바꾸어줌
class AssertionTest {

  @Test
  @DisplayName("스터디 만들기 - message 나타내기")
  void create_new_test() {
    Study study = new Study();
    assertNotNull(study);
    assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.");
  }

  @Test
  @DisplayName("스터디 만들기 - 람다식 사용하기")
  void create_new_test2() {
    Study study = new Study();
    assertNotNull(study);
    assertEquals(StudyStatus.DRAFT, study.getStatus(),
        () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + "여야 한다.");
  }

  @Test
  @DisplayName("스터디 만들기 - Supplier 사용")
  void create_new_test3() {
    Study study = new Study();
    assertNotNull(study);
    assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
      @Override
      public String get() {
        return "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.";
      }
    });
  }

  @Test
  @DisplayName("다양한 Assertions 적용")
  void assertionsTest() {
    Study study = new Study(1);
    // 한 번에 테스트가 실패한 테스트 모두를 알고싶을 때
    assertAll(
        () -> assertNotNull(study),
        () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
        () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 인원은 0보다 크다.")
    );
  }

  @Test
  @DisplayName("assertThrows 적용")
  void exceptionTest() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Study(-10));
    assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
  }

  @Test
  @DisplayName("assertTimeout 적용 - 코드가 모두 시행되고 테스트 결과 반환 - Duration보다 실행 시간이 길다면 실패")
  void assertTimeoutTest() {
    assertTimeout(Duration.ofMillis(400), () -> {
      new Study(10);
      Thread.sleep(300);
    });
  }

  @Test
  @DisplayName("assertTimeoutPreemptively 적용 - 코드가 모두 시행되고 테스트 결과 반환 - Duration보다 실행 시간이 길다면 실패")
  void assertTimeoutPreemptivelyTest() {
    // * 참고 : assertTimeoutPreemptively는 ThreadLocal을 사용한다면 예상치 못한 결과를 초래할 수 있다. 
    assertTimeoutPreemptively(Duration.ofMillis(400), () -> {
      new Study(10);
      Thread.sleep(300);
    });

  }


}