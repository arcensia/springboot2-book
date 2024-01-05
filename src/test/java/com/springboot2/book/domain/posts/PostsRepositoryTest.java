package com.springboot2.book.domain.posts;

import com.springboot2.book.domain.post.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
//    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void bring_gasigl(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content));

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        // assertThat(post.getTitle()).isEqualTo(title);
        // assertThat(post.getContent()).isEqualTo(content);


    }
}
