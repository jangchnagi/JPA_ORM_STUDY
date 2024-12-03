package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

public class JpaMain {

   public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();

        Team team= new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setName("member1");
        member.setTeam(team);
        em.persist(member);

        //조회
        Member findMember = em.find(Member.class,member.getId());
        //참조를 사용해서 연관관계 조회
        //Team findTeam = findMember.getTeam();

        //반대 방향으로 객체 그래프 탐색
        Team findTeam = em.find(Team.class,team.getId());
        int memberSize= findTeam.getMember().size();
        try{
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }
    }
}
