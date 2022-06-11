package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // JPA - 객체생성 (비영속)
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
            // JPA - 객체저장 (영속)
//            em.persist(member);

            // JPA - 조회
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // JPQL - 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5) // 페이지처리
//                    .setMaxResults(8)
//                    .getResultList();
//            for(Member member: result) {
//                System.out.println("member.name = " + member.getName());
//            }

            // JPA 삭제
//            em.remove(findMember);


            // 플러시 직접호출
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush();

            // 준영속 상태로 변경하기
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAAA");
//            em.detach(member);
            // 준영속상태가 되어 update 쿼리가 발생하지 않는다.

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); // **
            em.persist(member);
            
            // team.getMembers().add(member); // ** Member에서 set할때 값을 같이 넣어주고 여기는 생략해준다.

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members) {
                System.out.println("m = " + m.getUsername());
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
