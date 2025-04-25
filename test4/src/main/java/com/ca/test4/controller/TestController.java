package com.ca.test4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/user/hello")
    public String test1() {

        System.out.println("야호!!! test1 실행됨!!!");

        return "hello";
    }

    @RequestMapping("/shop/yyyy")
    public String test2() {
        System.out.println("qwer");
        return "qwer";
    }
}
