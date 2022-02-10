package com.spring.springtest.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // underscore를 공백으로 바꾸어줌
class RepeatTest {


  @RepeatedTest(10)
  @DisplayName("반복 테스트")
  void repeatTest(RepetitionInfo repetitionInfo) {
    log.info("test={} / {}", repetitionInfo.getCurrentRepetition(),
        repetitionInfo.getTotalRepetitions());
  }

  @DisplayName("반복 테스트 + 이름")
  @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
  void repeatWithNameTest(RepetitionInfo repetitionInfo) {
    log.info("test={} / {}", repetitionInfo.getCurrentRepetition(),
        repetitionInfo.getTotalRepetitions());
  }

}