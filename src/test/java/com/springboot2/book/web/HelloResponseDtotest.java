package com.springboot2.book.web;

import com.springboot2.book.web.dto.HelloResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
public class HelloResponseDtotest {
    @Test
    public void lombok_test(){
        String name = "test";
        int amount = 1000;
        HelloResponseDto dto= new HelloResponseDto(name, amount);
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);

//        원본소스
//        HelloResponseDto dto = new HelloResponseDto(name, amount);
//        assertThat(dto.getName().isEqualTo(name));
//        assertThat(dto.getAmount().isEqualTo(amount));
    }
}

// 문제1. assertThat를 그대로 사용하니 Cannot resolve method 'assertThat' in 'HelloResponseDtotest 오류가 발생하였다.
// 해결: Assertions를 직접불러와 assertThat를 호출하는 방식으로 변경되었다.

// 문제 2. RequiredArgsConstructor가 생성자를 만들지 못함.
// 해결: gradle버전이 맞지않아 lombok를 제대로 가져오지 못했다.
// 해결방법
// implementation 'org.projectlombok:lombok' 이는 gradle 5.x 이하 버전에서 동작한다. <- 책에서 사용하는 버전.
// gradle 5.x 이상 버전에서 lombok을 동작하도록 되어있어 현재 프로젝트인 Gradle 8.5에 동작하도록 변경하였다.
// compileOnly 'org.projectlombok:lombok'
// annotationProcessor 'org.projectlombok:lombok'