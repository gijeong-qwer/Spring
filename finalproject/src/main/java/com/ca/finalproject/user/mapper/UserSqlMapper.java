package com.ca.finalproject.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ca.finalproject.dto.UserDto;

@Mapper
public interface UserSqlMapper {   
    public void createUser(UserDto userDto);
}
