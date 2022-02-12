package com.spring.springtest.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.spring.springtest.domain.Member;
import com.spring.springtest.domain.Study;
import com.spring.springtest.member.MemberService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockitoTestBasic {

  @Test
  @DisplayName("Mockito 객체 클래스 내부사용")
  void createStudyService() {
    MemberService memberService = mock(MemberService.class);
    StudyRepository studyRepository = mock(StudyRepository.class);

    StudyService studyService = new StudyService(memberService, studyRepository);

  }

  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  @DisplayName("Mockito 객체 클래스 @Mock 어노테이션 사용")
  void createStudyService2() {
    StudyService studyService = new StudyService(memberService, studyRepository);

  }

  @Test
  @DisplayName("Mockito 객체 클래스 파라미터에 @Mock 어노테이션 사용")
  void createStudyService3(@Mock MemberService memberService,
      @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);
  }

  @Test
  @DisplayName("Stubbing Test")
  void stubbingTest(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("test@email.com");

    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(memberService.findById(any())).thenReturn(
        Optional.of(member)); // any() 는 파라미터를 어느것으로 하든 상관없다는 뜻

    Optional<Member> findById = memberService.findById(1L);
    assertEquals("test@email.com", findById.get().getEmail());
    Study study = new Study(10, "java");

    studyService.createNewStudy(1L, study);
  }

  @Test
  @DisplayName("Stubbing Exception Test")
  void stubbingExceptionTest(@Mock MemberService memberService,
      @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("test@email.com");

    when(memberService.findById(any())).thenReturn(
        Optional.of(member)); // any() 는 파라미터를 어느것으로 하든 상관없다는 뜻

    assertEquals("test@email.com", memberService.findById(1L).get().getEmail());
    assertEquals("test@email.com", memberService.findById(2L).get().getEmail());

    // 1 이라는 값이 들어올 경우 예외 던지기
//    when(memberService.findById(any())).thenThrow(new RuntimeException());

    // 1 이라는 값으로 validate 가 호출이 되었을 때 예외를 던져라
    doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

    assertThrows(IllegalArgumentException.class, () -> {
      memberService.validate(1L);
    });

    memberService.validate(2L);

    Study study = new Study(10, "java");

  }

  @Test
  @DisplayName("메소드가 여러번 호출될 때 호출되는 순서에 따라 다르게 Mocking")
  void createNewStudy(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setId(1L);
    member.setEmail("test@email.com");

    when(memberService.findById(any()))
        .thenReturn(Optional.of(member)) // 첫번째 호출
        .thenThrow(new RuntimeException()) // 두번째 호출
        .thenReturn(Optional.empty()); // 세번째 호출

    assertEquals("test@email.com", memberService.findById(1L).get().getEmail()); // 첫번째 호출
    assertThrows(RuntimeException.class, () -> { // 두번째 호출
      memberService.findById(2L);
    });
    assertEquals(Optional.empty(), memberService.findById(3L)); // 첫번째 호출

  }
}