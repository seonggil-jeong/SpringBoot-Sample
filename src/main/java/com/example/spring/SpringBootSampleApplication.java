package com.example.spring;

import com.example.spring.app.service.impl.AccountServiceImpl;
import com.example.spring.app.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootSampleApplication {
    private final AccountServiceImpl accountService;
    private final PostServiceImpl postService;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class, args);
    }


}
