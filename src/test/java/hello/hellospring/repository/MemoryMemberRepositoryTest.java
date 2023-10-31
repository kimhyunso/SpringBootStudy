package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {


    // 테스트는 순서와 상관없이 설계되어야 함 
    // 저장소, 공용 데이터 지워야함
    // TDD : 테스트를 먼저 만들고, 그에 따른 구현체를 만듬
    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = Member.builder()
                        .name("spring")
                        .build();

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));

        // Assertions.assertEquals(member, result);
        // Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member = Member.builder().name("spring1").build();
        repository.save(member);

        Member member1 = Member.builder().name("spring2").build();
        repository.save(member);


        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member);
    }


    @Test
    public void findAll(){
        Member member = Member.builder().name("spring1").build();
        repository.save(member);

        Member member1 = Member.builder().name("spring2").build();
        repository.save(member1);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
