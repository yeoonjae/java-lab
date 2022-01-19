package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

  private String id;
  private int level;

  public TraceId() {
    this.id = createId();
    this.level = 0;
  }

  private TraceId(String id, int level) {
    this.id = id;
    this.level = level;
  }

  private String createId() {
    // UUID 의 8자리만 사용
    return UUID.randomUUID().toString().substring(0,8);
  }

  public TraceId createNextId() {
    return new TraceId(id, level + 1);
  }
  private TraceId createPreviousId() {
    return new TraceId(id, level - 1);
  }

  private boolean isFirstLevel() {
    return level == 0;
  }

  public String getId() {
    return id;
  }

  public int getLevel() {
    return level;
  }
}
