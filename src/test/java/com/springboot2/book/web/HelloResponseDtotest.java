package com.springboot2.book.web;

import com.springboot2.book.web.dto.HelloResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
public class HelloResponseDtotest {
    @Test
    public void lombok_test(){
        String name = "test";
        int amount = 1000;
        HelloResponseDto dto= new HelloResponseDto(name, amount)
        Assertions.assertThat(dto.name()).isEqualTo(name);
        Assertions.assertThat(dto.amount()).isEqualTo(amount);

//        원본소스
//        HelloResponseDto dto = new HelloResponseDto(name, amount);
//        assertThat(dto.getName().isEqualTo(name));
//        assertThat(dto.getAmount().isEqualTo(amount));
    }
}
