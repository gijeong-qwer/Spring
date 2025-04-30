package com.ca.finalproject.user.mapper; // Mybatis에서 사용할 namespace임 

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ca.finalproject.dto.UserDto;

@Mapper
public interface UserSqlMapper {   
    //  select 일때는 특정 값을 리턴해야하고 ,그 이외 insert, update, delete는 void로 해도 무방하다 
    //  select만 결과를 우리가 볼 수 있었음 -> select는 리턴타입을 잘 써줘야함 


    //  동일한 아이디가 나올경우 List 쓰면됨 -> 똑같은 아이디 비밀번호 사실 예외처리 해야함 
    public void createUser(UserDto userDto); // 쿼리에 값의 변화를 주기 위해 UserDto 사용
    public UserDto findUserByAccountNameAndPassword(
        @Param("accountName")String accountName,
        @Param("password") String password);

    // 파라미터 하나일때는 Param 안붙여도됨
    // 2개이상일때만 문제 생기는거라 2개이상이면 꼭 붙여야함

    // 값에 해당하는 애들은 #id 같은 방식임 , dto(인스턴스)와는 다름 
    public UserDto findUserById(int id);


}
