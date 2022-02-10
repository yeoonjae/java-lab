package com.spring.springtest.domain;

import com.spring.springtest.study.StudyStatus;

public class Study {

  private StudyStatus studyStatus;

  private int limit;

  private String name;

  public Study() {
    this.studyStatus = StudyStatus.DRAFT;
  }

  public Study(int limit, String name) {
    this.limit = limit;
    this.name = name;
    this.studyStatus = StudyStatus.DRAFT;
  }

  public Study(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("limit은 0보다 커야 한다.");
    }
    this.limit = limit;
    this.studyStatus = StudyStatus.DRAFT;
  }

  public StudyStatus getStatus() {
    return this.studyStatus;
  }

  public int getLimit() {
    return limit;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Study{" +
        "studyStatus=" + studyStatus +
        ", limit=" + limit +
        ", name='" + name + '\'' +
        '}';
  }
}
