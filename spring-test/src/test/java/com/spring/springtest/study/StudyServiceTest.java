package com.spring.springtest.study;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spring.springtest.domain.Member;
import com.spring.springtest.domain.Study;
import com.spring.springtest.member.MemberService;
import java.util.Optional;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  void createNewStudyTest() {
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setEmail("member@email.com");
    member.setId(1L);

    Study study = new Study(10, "test");
    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(studyRepository.save(study)).thenReturn(study);

    studyService.createNewStudy(1L, study);

    assertNotNull(study.getOwnerId());
    assertEquals(member.getId(), study.getOwnerId());

  }

  @Test
  void createNewStudyVerifyTest() {
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setEmail("member@email.com");
    member.setId(1L);

    Study study = new Study(10, "test");
    when(memberService.findById(1L)).thenReturn(Optional.of(member));
    when(studyRepository.save(study)).thenReturn(study);

    studyService.createNewStudy(1L, study);

    assertNotNull(study.getOwnerId());
    assertEquals(member.getId(), study.getOwnerId());

    verify(memberService, times(1)).notify(study);
    verify(memberService, never()).validate(any());
  }

  @DisplayName("BDD 스타일로 적용")
  @Test
  void createNewStudyBDDTest() {

    // Given
    StudyService studyService = new StudyService(memberService, studyRepository);

    Member member = new Member();
    member.setEmail("member@email.com");
    member.setId(1L);

    Study study = new Study(10, "test");
    given(memberService.findById(1L)).willReturn(Optional.of(member));
    given(studyRepository.save(study)).willReturn(study);

    // When
    studyService.createNewStudy(1L, study);

    // Then
    assertNotNull(study.getOwnerId());
    assertEquals(member.getId(), study.getOwnerId());
    then(memberService).should(times(1)).notify(study);
    then(memberService).shouldHaveNoMoreInteractions();
  }

}