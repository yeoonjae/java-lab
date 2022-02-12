package com.spring.springtest.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.spring.springtest.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // underscore를 공백으로 바꾸어줌
class ParameterTest {

  @DisplayName("파라미터로 테스트")
  @ParameterizedTest
  @ValueSource(strings = {"파라미터로", "테스트", "해보기"})
  void parameterTest(String message) {
    log.info("message={}", message);
  }

  @DisplayName("파라미터로 테스트 name 속성 추가")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"파라미터로", "테스트", "해보기"})
  void parameterWithNameTest(String message) {
    log.info("message={}", message);
  }

  @DisplayName("Null, EmptySource 까지 테스트 -> 테스트 개수 증가")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"파라미터로", "테스트", "해보기"})
  @EmptySource
  @NullSource
  void parameterTest2(String message) {
    log.info("message={}", message);
  }

  @DisplayName("파라미터로 테스트 - Study 타입 받기")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(ints = {10, 20, 30})
  void parameterTest3(@ConvertWith(StudyConverter.class) Study study) {
    log.info("message={}", study.getLimitCount());
  }

  @DisplayName("파라미터로 테스트 - CsvSource 사용하기 (두개의 인자로 받기)")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @CsvSource({"10, '자바 스터디'", "20, `스프링`"})
  void parameterTest4(Integer limit, String name) {
    log.info("study={}", new Study(limit, name));
  }

  @DisplayName("파라미터로 테스트 - CsvSource 사용하기 (하나의 Study로 받기)")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @CsvSource({"10, '자바 스터디'", "20, `스프링`"})
  void parameterTest4(ArgumentsAccessor argumentsAccessor) {
    log.info("study={}", new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1)));
  }

  @DisplayName("파라미터로 테스트 - ArgumentsAggregator 만들어 사용")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @CsvSource({"10, '자바 스터디'", "20, `스프링`"})
  void parameterTest4(@AggregateWith(StudyAggregator.class) Study study) {
    log.info("study={}", study);
  }

  static class StudyAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
        throws ArgumentsAggregationException {
      return new Study(accessor.getInteger(0), accessor.getString(1));
    }
  }

  static class StudyConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType)
        throws ArgumentConversionException {
      assertEquals(Study.class, targetType, "Can only convert to Study");
      return new Study(Integer.parseInt(source.toString()));
    }
  }
}