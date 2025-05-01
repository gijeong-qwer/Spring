package com.ca.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ca.finalproject.dto.ArticleDto;

@Mapper
public interface BoardSqlMapper {

    public void createArticle(ArticleDto articleDto);

    //  게시글 번호 순서로 내림차순으로 전체 정보를 가져올것임
    public List<ArticleDto> findAll();

    // id로 받는이유 - 명확함 title로 받으면 중복아이디 같은거 어떻게 처리할건데
    // 닉네임 UserDto로 받는거 이것도 말이안됨 여러게시글 중 하나를 어떻게 불러올건데
    // id로 받는게 맞네

    // 상세글 보기 - 게시글 id로 찾을거임.. 
    public ArticleDto findArticleById(int id);

    // 조회수 증가
    public void increaseReadCount(int id);

    // 글 삭제
    public void deleteArticleById(int id);

    // 글 수정
    public void updateArticleById(ArticleDto articleDto);

}
