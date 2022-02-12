package com.spring.springtest.member;

import com.spring.springtest.domain.Member;
import com.spring.springtest.domain.Study;
import java.util.Optional;

public interface MemberService {

  Optional<Member> findById(Long memberId);

  void validate(Long memberId);

  void notify(Study newstudy);

  void notify(Member member);
}
