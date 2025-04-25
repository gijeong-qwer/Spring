package com.ca.test10.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/* 
 "이 클래스는 스프링이 알아서 관리해줘!" 라고 스프링에게 알려주는 표시
 new를 하지 않았지만 annotation Component 가 붙었으면 인스턴스가 생성이됨 

 @Component는 스프링이 "자동으로 객체 만들어서 관리하라"고 표시하는 것.
스프링은 그걸 리플렉션으로 클래스 찾아서 자동 생성해주는 거야.
*/

@Service
public class TestService {

    public TestService() {
        System.out.println("야호!!!!");
    }

    public void serviceMethod1() {

    }

    public void serviceMethod2() {

    }
}
