package com.study.springjpabasic.hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

  @Id
  private Long id;
  private String name;

}
