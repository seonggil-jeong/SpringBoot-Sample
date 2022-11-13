package com.example.spring.exception.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorResult {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "cannot found post"),
    ;


    private final HttpStatus status;
    private final String message;
}
