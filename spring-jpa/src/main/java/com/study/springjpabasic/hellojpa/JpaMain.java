package com.study.springjpabasic.hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    // Application 로딩시점에 EntityManagerFactory는 하나만 만든다. 트랜잭션 즉, 하나의 일련의 활동을 할 때마다 EntityManager를 만들어야한다.
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 등록
      /*Member member = new Member();
      member.setId(3L);
      member.setName("HelloC");
      em.persist(member);*/

      // 조회
      /*Member findMember = em.find(Member.class, 1L);
      System.out.println("findMember = "+findMember.getId());
      System.out.println("findMember = "+findMember.getName()); */

      // 수정
      // findMember.setName("HelloJPA2"); // em.persist(findMember); 안해도됨

      // 전체 회원 조회
      List<Member> reuslt = em.createQuery("select m from Member as m", Member.class)
          .setFirstResult(0)
          .setMaxResults(2)
          .getResultList();

      for (Member member : reuslt) {
        System.out.println("member.name : " + member.getName());
      }


      tx.commit();


      /**
       * 주의
       * 1. 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
       * 2. 엔티티 매니저는 쓰레드간 공유 절대 XXXX (사용하고 버려야한다.)
       * 3. JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
       * */
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();


  }

}
