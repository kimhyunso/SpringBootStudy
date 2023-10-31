package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }


    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

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
    public void 중복회원예외(){
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
    void findMembers() {




    }

    @Test
    void findOne() {
    }
}