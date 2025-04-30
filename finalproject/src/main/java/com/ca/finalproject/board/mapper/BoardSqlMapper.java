package com.ca.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ca.finalproject.dto.ArticleDto;

@Mapper
public interface BoardSqlMapper {

    public void createArticle(ArticleDto articleDto);
    //  게시글 번호 순서로 내림차순으로 전체 정보를 가져올것임
    public List<ArticleDto> findAll();

}
