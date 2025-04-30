package com.ca.finalproject.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ArticleDto {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private LocalDateTime createdAt;
}
