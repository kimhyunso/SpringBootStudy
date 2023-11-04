package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


// 통합 테스트

// Transactional : 테스트케이스를 달면 Trasaction 먼저 실행, 테스트 끝나고, 롤백해줌 [실제 데이터 반영을 하지 않음]
// SpringBootTest 스프링 컨테이너와 테스트를 함께 실행한다.
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired  MemberService service;
    @Autowired
    MemberRepository repository;

    // @Commit
    @Test
    void 회원가입() {
        // given
        Member member = Member.builder().name("spring").build();

        // when
        Long saveId = service.join(member);

        // then
        Member findMember = service.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        // given
        Member member1 = Member.builder().name("spring").build();
        Member member2 = Member.builder().name("spring").build();

        // when
        service.join(member1);
        /*try{
            service.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }


    @Test
    void 모든회원조회() {

        List<Member> members = service.findMembers();
        assertThat(members).isNotNull();

    }

    @Test
    void findOne() {
    }
}