package com.ca.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ca.finalproject.dto.UserDto;
import com.ca.finalproject.user.service.UserServiceImpl;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired // container에 DI 부탁
    private UserServiceImpl userService;

    @RequestMapping("loginPage")
    public String loginPage(){  
        
        return "user/loginPage";  // 폴더 구조가 될 것임 // 포워딩
    }

    @RequestMapping("registerPage")
    public String registerPage(){

        return "user/registerPage";
    }

    @RequestMapping("registerProcess")  //registerProcess가 실행됬을때 ModelAttribute가 작동됨  
    public String registerProcess( 
        @ModelAttribute UserDto params  // @ModelAttribute가 빠져도 동작: Default가 ModelAttribute임 -> 명시적으로 보여주기위함 //외부 패키지 항상 import 생각 
    ){ 
        System.out.println(params);  // 입력 잘받는지 확인하는 test코드 

        userService.register(params);

        return "user/registerComplete";
    }


}
