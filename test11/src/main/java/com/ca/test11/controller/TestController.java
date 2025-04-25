package com.ca.test11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ca.test11.dto.Test1RequestDto;

@Controller
@RequestMapping("user/my")
public class TestController {

    // Controller에 있는 메서드를 우리가 이제는 호출하지 않을것임
    // 해당메서드와 URL 요청(Path)을 연결하겠다 => 맵핑하겠다
    @RequestMapping("test1")
    public String test1(
            @RequestParam("a") int value, // 사실 RequestParam안해도 받아짐
            @RequestParam("b") int b,
            @RequestParam("qqq") int qqq) {
        System.out.println("test1이 호출되었을 때 실행될 코드");
        System.out.println("a= " + value);
        System.out.println("b= " + b);
        System.out.println("qqq= " + qqq);
        return "test1";
    }

    @RequestMapping("test2")
    public String test2(
            @ModelAttribute Test1RequestDto requestDto) {
        System.out.println("test1이 호출되었을 때 실행될 코드");
        System.out.println(requestDto);
        return "test1";
    }

    @RequestMapping("test3/{a}/{b}") // test3/아무거나1/아무거나2 ex) test3/10/20 => Path Variable
    public String test3(
            @PathVariable("a") int a,
            @PathVariable("b") int b) {
        System.out.println(a);
        System.out.println(b);
        return "test1";
    }

    // @RequestMapping("")
    // public String test3() {
    // System.out.println("test2이 호출되었을 때 실행될 코드");
    // return "test1";
    // }

}
