package io.github.jr.developers.club;

import io.github.jr.developers.club.jpa.club.Club;
import io.github.jr.developers.club.jpa.club.ClubRepository;
import io.github.jr.developers.club.jpa.member.Member;
import io.github.jr.developers.club.jpa.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootApplication
@RestController
public class ClubApplication {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager clubEntityManager;

    @PersistenceContext(unitName = "memberEntityManager")
    private EntityManager memberEntityManager;


    public static void main(String[] args) {
        SpringApplication.run(ClubApplication.class, args);
    }

    @GetMapping("/spring-data-jpa/club")
    public List<Club> retrieveClubs() {
        return clubRepository.findAll();
    }

    @GetMapping("/spring-data-jpa/member")
    public List<Member> retrieveMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/hibernate/club/{clubName}")
    public Club retrieveByClubName2(@PathVariable("clubName") String clubName) {
        return clubEntityManager
                .createQuery("select c from Club c where c.clubName = :clubName", Club.class)
                .setParameter("clubName", clubName)
                .getSingleResult();
    }

    @GetMapping("/hibernate/member/{memberName}")
    public Member retrieveByMemberName2(@PathVariable("memberName") String memberName) {
        return memberEntityManager
                .createQuery("select m from Member m where m.memberName = :memberName", Member.class)
                .setParameter("memberName", memberName)
                .getSingleResult();
    }
}
