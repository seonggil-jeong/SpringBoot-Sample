package com.example.spring.exception.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorResult {
    EXPIRED_JWT_TOKEN(HttpStatus.FORBIDDEN, "token was Expired"),
    ;


    private final HttpStatus status;
    private final String message;
}
