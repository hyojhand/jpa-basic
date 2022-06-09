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
            // JPA 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            // JPA 조회
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            // JPQL 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5) // 페이지처리
                    .setMaxResults(8)
                    .getResultList();
            for(Member member: result) {
                System.out.println("member.name = " + member.getName());
            }


            // JPA 삭제
//            em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
