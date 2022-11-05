package com.example.spring.exceptions;

import com.example.spring.exceptions.results.JwtErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtException extends RuntimeException {


    private final JwtErrorResult errorResult;
}
