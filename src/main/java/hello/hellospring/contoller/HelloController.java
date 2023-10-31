package hello.hellospring.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello";
    }


    // viewResolver 확인 후 templates에서 찾음
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // viewResolver 대신 HttpMessageConverter
    // StringConverter :: 문자
    @ResponseBody
    @GetMapping("hello-string")
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring"
    }



    // 객체가 오면 json방식으로 반환
    // viewResolver 대신 HttpMessageConverter
    // JsonConverter :: 객체, StringConverter :: 문자
    // MappingJackson2HttpMessageConverter
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
