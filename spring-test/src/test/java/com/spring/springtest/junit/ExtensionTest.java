package com.spring.springtest.junit;

import com.spring.springtest.junit.annotation.FastTest;
import com.spring.springtest.junit.annotation.SlowTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

@Slf4j
public class ExtensionTest {

  @RegisterExtension
  static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

  @FastTest
  void create() throws InterruptedException {
    Thread.sleep(1005L);
    log.info("create 실행");
  }

  @SlowTest
  void create1() {
    log.info("create1 실행");
  }
}
