package hello.advanced.trace;
/**
 * 로그의 상태 정보를 나타낸다.
 * */
public class TraceStatus {

  private TraceId traceId;
  private Long startTimeMs; // 로그 시작시간. 종료 시 시작시간을 기준으로 전체 수행시간을 구함
  private String message; // 시작시 사용한 메세지지

 public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
    this.traceId = traceId;
    this.startTimeMs = startTimeMs;
    this.message = message;
  }

  public TraceId getTraceId() {
    return traceId;
  }

  public Long getStartTimeMs() {
    return startTimeMs;
  }

  public String getMessage() {
    return message;
  }
}
