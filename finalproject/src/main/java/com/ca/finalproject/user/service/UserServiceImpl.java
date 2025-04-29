package com.ca.finalproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ca.finalproject.dto.UserDto;
import com.ca.finalproject.user.mapper.UserSqlMapper;

@Service
public class UserServiceImpl {

    @Autowired
    private UserSqlMapper userSqlMapper; 

    public void register(UserDto UserDto){
        userSqlMapper.createUser(UserDto);
    }

    public UserDto getUserByAccountNameAndPassword(String accountName, String password){
        UserDto userDto = userSqlMapper.findUserByAccountNameAndPassword(accountName,password);
        // 정상 입력시 Dto에 정보 다 받아서 return, 잘못이력시 null 나옴 
        // 조건에 안맞는 값으로 쿼리를 작성하면 null값이 나옴 **
        return userDto;
    }

}
