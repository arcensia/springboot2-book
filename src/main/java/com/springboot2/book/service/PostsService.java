package com.springboot2.book.service;


import com.springboot2.book.domain.post.Posts;
import com.springboot2.book.domain.post.PostsRepository;
import com.springboot2.book.web.dto.PostsResponseDto;
import com.springboot2.book.web.dto.PostsSaveRequestDto;
import com.springboot2.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transient
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId();

    }

    @Transient
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id: " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id: " + id));
        return new PostsResponseDto(entity);
    }
}
