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


    // 특정 구조를 보고 메모리구조가 어떻게 구성될지 쫙쫙 그려질 정도로 하는게 강사님이 원하는 기준
    // 할만한데 속도가 좀 느림 
    public Map<String , Object> getArticle(int id){  // 구분하기 힘든사람들은 articleId로 써도됨 , 귀찮아서 id id 쓰고있는거임 

        // 글을 볼때 조회수가 증가하는 쿼리 -> 수정할때도 증가하는데... 흠.. 근데 조회수 수정할때도 증가하게 냅두는 사이트가 많다함
        // 수정을 따로 하는것보다 가지고 오는게 더 좋음 나중에도 파라미터로 가져오는것보다
        boardSqlMapper.increaseReadCount(id);

        // 작성자가 나와야 하므로 Map임 
        Map<String,Object> map = new HashMap<>();
        // 게시글을 가져오는데 이곳엔 , 닉네임이 존재하지않음
        ArticleDto articleDto = boardSqlMapper.findArticleById(id);
        //  게시글의 유저아이디를 기반으로 UserDto의 Id에 대입해서 해당 아이디에 대한 Dto를 가져옴
        UserDto userDto = userSqlMapper.findUserById(articleDto.getUserId());
        map.put("articleDto", articleDto);
        map.put("userDto",userDto);

        return map; 
    }

    public void deleArticle(int id){
        boardSqlMapper.deleteArticleById(id);
    }

    public void updateArticle(ArticleDto articleDto){
        boardSqlMapper.updateArticleById(articleDto);
    }


}
