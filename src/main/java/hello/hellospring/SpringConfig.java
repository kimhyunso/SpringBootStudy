package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

// 어셈블리 : 어플리케이션을 조립한다.
// 개방 폐쇄 원칙 (OCP) : 확장에는 열려있고, 수정(변경)에는 닫혀있다.
@Configuration
public class SpringConfig {

    /*private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    // @PersistenceContext
    private EntityManager em;
    private final MemberRepository memberRepository;


    @Autowired
    public SpringConfig(EntityManager em, MemberRepository memberRepository) {
        this.em = em;
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

   /* @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return  new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);

    }*/
}
