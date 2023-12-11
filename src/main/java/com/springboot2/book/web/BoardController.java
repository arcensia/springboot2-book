package com.springboot2.book.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @GetMapping("/board")
    public String Board(){
        return "Board";
    }
}
