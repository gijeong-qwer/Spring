package com.ca.test10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ca.test10.dto.StudentDto;
import com.ca.test10.service.CarService;
import com.ca.test10.service.TestService;

/* 
======================================================================

    Spring Framework = Ioc, DI  << 지원하기위한 Container  기술
    의존도 낮추기 위해서는 IOC (Inverstion of Control) : 제어의 역행
    DI (Dependency Injection)
    앞으로 우리는 new 키워드를 최소화 할 것임 (단, DTO 제외 )
    실행 객체는 new를 하지 않을 것임 
    클래스는 크게 두가지 DTO와 Component
    
======================================================================
*/

/* 
=========================================================================================

    Controller Service Repository는 Component를 포함한 메타 애노테이션이다 ( 상속비슷한거임 )

    해당4개의 annotation들은 코드가 작동했을때 container에 인스턴스를 생성시킨다 

    @Component : 3Layers 아키텍쳐에서 벗어나면 Component를 붙임 ** (MVC 계층에 명확히 속하지 않는, 기타 기능 클래스에 @Component를 붙인다)
    3Layers - architecture 
    @Controller
    @Service
    @Repository
   -> 위와 같은 어노테이션들을 '설정'이라고 부름 

    container 의 가장 중요한 개념 
    1. 인스턴스 생성  // 생성 및 보관 
    2. Dependency Injection : 의존성 주입을 제공 

    DI
    1. 생성자 주입 -> 파라미터로 생성자내부에 주입
    @RequiredArgsConstructor 
    근데 final일때만만 사용가능 - 요즘은 1을 더 많이 사용함 , 그러나 우리 배울땐 Autowired사용할것임 
    2. 세터주입 
    @Autowired

    *** 상속받은 녀석이 두명일 때 논리적으로 충돌 발생 -> 서버가 가동이 안됨  !!!! 충돌 주의!!!! 제일 중요
    우리는 인터페이스를 제작하고 인터페이스에 대응하는 클래스를 하나만 정의할 것임 
    컨트롤러에 주입될때는 인터페이스로 변수를 정의할것이고 , 다형성을 이용해서 주입할 것임 

=========================================================================================
*/

// @Component  = 컨테이너에 인스턴스 생성

// @Service
// @Repository - DB로 구현할거라 요즘엔 잘 사용 안함

// @RequiredArgsConstructor
@Controller
public class TestController {

    @Autowired // 세터주입 : setter 주입
    private TestService testService; // 클래스 직접 사용하는거 (비정석)

    /*
     * =============================================================================
     * 
     * 
     * 위에처럼 클래스를 인터페이스사용 없이 사용하면 안됨 - 실무에서 ( 인터페이스 쓰고 , 상속받을 클래스를 정의하고 , 인터페이스 참조변수
     * 만들고 다형성으로 클래스의 인스턴스 가져오기 !!!) : 정석
     * 인터페이스 나누는이유 : 모듈간의 교체 원활하게 하기 위해 // 수정하기 쉽게하기위해
     * TDD : 테스트 드라이브 // 테스트코드 먼저짜고 하는거 구현체는 없어도 인터페이스는 존재해야함 >> 내부적으로 모듈 교체하는 방식 -
     * 정처기에서 배움
     * TDD 해보면 이렇게 코드 못짠다는거 알 수 있음 ... 불가능의 수준 => 정석 , 정석인데 사실상 불가능함
     * 인터페이스를 사용한다는것은 기능 구현을 완벽하게 분리했다는것 .. 참고로 인터페이스는 무지성 인터페이스에대한 비난도 받고있음
     * 
     * =============================================================================
     */

    @Autowired // 상속받은 녀석 다형성으로 데려옴 -> 2번짜 방식으로 인터페이스 만들어서 사용하는거 연습 (정석) 위에 1번은 우리가 할 비정석
    private CarService qwerService; // 상속받은 녀석이 두명일 때 논리적으로 충돌 발생 -> 서버가 가동이 안됨

    public void controllerMethod1() {
        testService.serviceMethod1();
    }

    public void controllerMethod2() {
        StudentDto st1 = new StudentDto();

        st1.setAge(13);
    }
}
