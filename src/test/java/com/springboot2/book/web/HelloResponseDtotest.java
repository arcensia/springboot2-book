package com.springboot2.book.web;

import com.springboot2.book.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
public class HelloResponseDtotest {
    @Test
    public void lombok_test(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        Assertions.assertThat(dto.getName().isEqualTo(name));
        Assertions.assertThat(dto.getAmount().isEqualTo(amount));
//        assertThat(dto.getName().isEqualTo(name));
//        assertThat(dto.getAmount().isEqualTo(amount));
    }
}
