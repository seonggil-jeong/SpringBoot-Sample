package com.example.spring.exception;

import com.example.spring.exception.result.PostErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostException extends RuntimeException {


    private final PostErrorResult errorResult;



}
