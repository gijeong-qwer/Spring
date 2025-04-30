package com.ca.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.finalproject.board.mapper.BoardSqlMapper;
import com.ca.finalproject.dto.ArticleDto;
import com.ca.finalproject.dto.UserDto;
import com.ca.finalproject.user.mapper.UserSqlMapper;

@Service
public class BoardServiceImpl {

    @Autowired
    private BoardSqlMapper boardSqlMapper; 

    @Autowired
    private UserSqlMapper userSqlMapper;
    
    public void writeArticle(ArticleDto articleDto){  
        boardSqlMapper.createArticle(articleDto);
    }


    // Java로 논리적으로 JOIN하는 방법, 자료구조가 완벽하지 않으면 안됨
    // 게시판 이상으로 만들 수가 없음
    public List<Map<String,Object>> getArticleList(){
        List<Map<String,Object>> dataList = new ArrayList<>();

        List<ArticleDto> articleDtoList =boardSqlMapper.findAll();
        
        //  아래 코드는 N+1 문제가 생김: 퍼포먼스적으로 문제  그러나 현재는 자료구조 형태의 연습을 위해 사용
        //  조인을 시키면 쉬워지는 경우가 있는데 잘못 습관이 들어서 말도 안되는 JOIN들을 함 
        //  쿼리는 되는데 자료구조가 안되는 상황이옴 
        // 내부조인 시킨것 -> ArticleDto 와 UserDto를 엮음
        // HashMap을 사용할것임 , 자료구조가 모든것을 해결해줄것임 
        for(ArticleDto articleDto :articleDtoList){
            // 한바퀴 돌때마다 Dto끼리 결합시킬것임
          Map<String,Object> map = new HashMap<>();
          UserDto userDto  = userSqlMapper.findUserById(articleDto.getUserId());   // getId 하면 글번호 나옴 주의!!!!
          map.put("userDto", userDto); // 키이름은 아무렇게나 설정해도됨
          map.put("articleDto", articleDto); // map은 다른형태의 자료를 묶는데 최적화 되어있음
          //  한바퀴돌때마다 map은 메모리가 소멸됨 -> List에 map을 다시 담아야함 
          dataList.add(map);

        }

        return dataList;
    }
}
