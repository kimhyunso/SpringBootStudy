package hello.hellospring.contoller;

import hello.hellospring.domain.Member;
import hello.hellospring.dto.MemberForm;
import hello.hellospring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    // 필드 주입
    /*@Autowired
    private final MemberService memberService;*/
    private final MemberService memberService;

    // 생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        // 프록시
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/new")
    public String createForm(){
        return "members/createMemberForm";
    }


    @PostMapping("/new")
    public String create(MemberForm form){
        Member member = Member.builder().name(form.getName()).build();
        memberService.join(member);
        log.info("member = " + form.getName());
        return "redirect:/";
    }

    // Setter 주입
    /*@Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }*/

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }

}
