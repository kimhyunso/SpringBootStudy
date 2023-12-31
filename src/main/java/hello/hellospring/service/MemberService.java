package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {
    // service : 비지니스에 의존적
    // repository : 개발적

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }


    /**
     *  회원가입
     */
    public Long join(Member member){
        // 시간 찍기 : 공통관심사항
       /* long start = System.currentTimeMillis();
        try{
            // 같은 이름이 있는 중복 회원 x
        }finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("join = " + timeMs + "ms");
        }*/

        // 핵심관심사항
        extracted(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void extracted(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        // 시간 찍기 : 공통관심사항
        /*long start = System.currentTimeMillis();
        try{
        }finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }*/
        // 핵심관심사항
        return memberRepository.findAll();
    }


    /**
     * 하나 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
