package com.ca.finalproject.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data  // 생성자 setter getter toString을 자동으로 만들어줌 
public class UserDto {  // DB 기준 필요한 DTO들을 생성해야함 
    private int id;
    // mybatis.configuration.map-underscore-to-camel-case=true  -> application.properties에서 설정한것 때문에 account_name대신 accountName써야함(카멜케이스)
    private String accountName ;
    private String password;
    private String nickname;
    private String email;
    private String gender;  // boolean 대신  M F 로 받을것임  
    private LocalDate birth;    // java.util 대신 추가한것 이게 더 명확하긴한데 어려움 
    private String phone;
    private LocalDateTime createdAt;

}
