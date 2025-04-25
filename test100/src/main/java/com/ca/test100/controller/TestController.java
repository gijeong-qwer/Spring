package com.ca.test100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("test1")
    public String test1() {

        System.out.println("test1호출시 실행될 코드");
        return "test1";
    }

    @RequestMapping("")
    public String test2() {
        return "test1";
    }

}
