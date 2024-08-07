package com.springboot2.book.domain.posts;

import com.springboot2.book.domain.post.Posts;
import com.springboot2.book.domain.post.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
//    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void bring_board(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("zxcklwe@naver.com")
                        .build());

//        postsRepository.save(Posts.builder()
//                .title(title)
//                .content(content));

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.getFirst();
//        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2024,7,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate= " + posts.getCreateTime() + ", modifiedDate= " + posts.getModifiedDate());
    }
}
