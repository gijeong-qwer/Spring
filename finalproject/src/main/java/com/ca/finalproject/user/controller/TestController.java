package com.ca.finalproject.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;

@Controller
public class TestController {

    @RequestMapping("test1")
    public String test1(HttpSession session , HttpServletResponse res){

        //  접속자당 다르게 저장되어야할 메모리가 필요하다
        //  사실 이러한 방식은 데이터베이스에 저장해야하는게 가장 정석
        //  1번 방식 session

        String value = "clickedlist";

        // session.setAttribute("list", value); // session은 서버쪽의 RAM

        //  클라이언트 쿠키로 저장  -> 쿠키 한글 특수문자 못넣음 가벼운것만 가능 , 가능한특수문자는 url에 쓸 수 있는것 정도
        //  특히 F12넣으면 쿠키변조, 탈취같은게 가능해서 보안쪽으로는 상당히 취약함 
        //  이런용도로 쿠키에 저장은 요즘시대에는 잘안함
        //  spring프레임워크 내부의 Tomcat은 JSESSIONID 으로 쿠키를 자동 저장한다 
                    
                    
                  
        Cookie ccc = new Cookie("qwer", value);
        res.addCookie(ccc);

        return "user/loginPage";
    }
}
