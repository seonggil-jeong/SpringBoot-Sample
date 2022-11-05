package com.example.spring.exceptions;

import com.example.spring.exceptions.results.PostErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostException extends RuntimeException {


    private final PostErrorResult errorResult;



}
