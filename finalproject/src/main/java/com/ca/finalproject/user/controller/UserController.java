package com.ca.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ca.finalproject.dto.UserDto;
import com.ca.finalproject.user.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

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


    // 세션인증방식으로 작성한 코드 
    @RequestMapping("loginProcess")
    public String loginProcess( 
        HttpSession session,
        @RequestParam("accountName") String accountName,   
        @RequestParam("password") String password) {       
          
        // 모든 정보를 뽑아옴           
        UserDto userDto = userService.getUserByAccountNameAndPassword(accountName, password);
        if(userDto !=null) {
            // 인증성공  , 세션을 이용해야함 (Spring에게 session 저장공간을 달라고 할 수 있음)
            // 세션 저장공간은 접속당 공간이 한개씩 생김 -> 이곳에 UserDto 저장

            // redirect는 로그인하면 와 !! 로그인했습니다 같은거 안뜨게함
            // 간단하게 표현하면 과정생략임 >> 중요도 문제 , 표현이 필요 없을거 같을때 redirect 사용
            // 출력과정 생략인데 , 너무 줄여도 (ex회원가입을 성공했습니다)같은거를 없애면 불편함
            session.setAttribute("sessionUserInfo", userDto); // 맵형태의 자료구조 ,해시맵 : 접속당 사용할 수 있는 자료구조
            return "redirect:/board/mainPage";  
            // mainboard 구현 board패키지를 추가함 
        }else{
            // 인증실패
            return "user/loginFail";
        }

        // return "??"; 
    }

    @RequestMapping("logoutProcess")
    public String logoutProcess(HttpSession session){
        session.invalidate();  // invalidate = 무효화하다 
        //  로그아웃시 세션값 다 날림 
        return "redirect:/board/mainPage";
    }

}
