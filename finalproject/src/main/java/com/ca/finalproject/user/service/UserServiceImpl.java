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

}
