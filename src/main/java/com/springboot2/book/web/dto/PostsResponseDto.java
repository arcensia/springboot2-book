package com.springboot2.book.web.dto;

import com.springboot2.book.domain.post.Posts;

public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title= entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
