package com.example.spring.exception;

import com.example.spring.exception.result.JwtErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtException extends RuntimeException {


    private final JwtErrorResult errorResult;
}
