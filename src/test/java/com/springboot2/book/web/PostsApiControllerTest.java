package com.springboot2.book.web;


import com.springboot2.book.domain.post.PostsRepository;
import com.springboot2.book.dto.PostsSaveRequestDto;
import com.springboot2.book.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostsApiControllerTest {

    private final PostsService postsService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;


    @AfterEach
    public void tearDown(){

    }

    @Test
    public void Posts_save() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String auth = "auth";

        String url = "\"http://localhost:\" + port + \"/api/v1/posts";

        //when
        ResponseEntity<Long› responseEntity - restTemplate.postForEntity(url, requestDto, Long.class);
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);

    }
}



// 문제 1.
// test Code에서 lombok을 찾지 못한다.
// 원인: 현재 적용한 lombok 플러그인은 테스트 환경에서 의존성을 가지고 있지 않았다.
// 해결: 그레이들에 테스트 존성 scope를 추가하였다.