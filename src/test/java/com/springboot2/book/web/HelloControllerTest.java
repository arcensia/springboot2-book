package com.springboot2.book.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest //@ExtendWith(SpringExtension.class) <- @RunWith(SpringRunner.class) junit5로 넘어오면서 사라졌다.
//                // 또한 @SpringBootTest 어노테이션에 포함되도록 변경되었다.
//@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void return_hello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

//      책에 나온 내용
//        mvc.perform(get("/hello"))
//                .andExpect(statues().isOk())
//                .andExpect(content().string(hello));
    }

}
