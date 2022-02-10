package com.spring.springtest.junit;

import com.spring.springtest.junit.annotation.SlowTest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

@Slf4j
public class FindSlowTestExtension implements BeforeTestExecutionCallback,
    AfterTestExecutionCallback {

  private long THRESHOLD;

  public FindSlowTestExtension(Long THRESHOLD) {
    this.THRESHOLD = THRESHOLD;
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    Store store = getStore(context);
    store.put("START_TIME", System.currentTimeMillis());
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    Method testMethod = context.getRequiredTestMethod();
    SlowTest annotation = testMethod.getAnnotation(SlowTest.class);
    Store store = getStore(context);
    long start_time = store.remove("START_TIME", long.class);
    long duration = System.currentTimeMillis() - start_time;
    if (duration > THRESHOLD && annotation == null) {
      log.info("Please consider mark method {} with @SlowTest.", testMethod.getName());
    }
  }

  private Store getStore(ExtensionContext context) {
    String testClassName = context.getRequiredTestClass().getName();
    String testMethodName = context.getRequiredTestMethod().getName();
    return context.getStore(Namespace.create(testClassName, testMethodName));
  }
}
