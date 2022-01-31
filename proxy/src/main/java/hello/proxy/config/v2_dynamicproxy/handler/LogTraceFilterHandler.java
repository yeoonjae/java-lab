package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;

public class LogTraceFilterHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace trace;
  private final String[] patterns;

  public LogTraceFilterHandler(Object target, LogTrace trace, String[] patterns) {
    this.target = target;
    this.trace = trace;
    this.patterns = patterns;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    // 메소드 이름 필터
    String methodName = method.getName();
    if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
      return method.invoke(target, args);
    }

    TraceStatus status = null;
    try {
      String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
      status = trace.begin(message);
      //target 호출
      Object result = method.invoke(target, args);
      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
