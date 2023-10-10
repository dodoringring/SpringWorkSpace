package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")//매핑 해주기 get방식 메소드가 실행이 된다.
    public String hello(Model model){
        //스프링에서 모델에다 값을 넣어줌
        model.addAttribute("data","hello!!!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    //
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";

    }
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;//hello spring
    }
    /*진짜는 지금부터입니다^^*/
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{//helloCotroller.Hello 자바에서 허용하는 문법
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


/*
* 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리한다.
* 스프링 부트 템플릿엔진 기본 viewName매핑
* `resource:templates/`+{ViewName}+`html`
* */

