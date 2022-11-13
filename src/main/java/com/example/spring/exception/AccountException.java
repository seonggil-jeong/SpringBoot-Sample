package com.example.spring.exception;

import com.example.spring.exception.result.AccountErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountException extends RuntimeException {


    private final AccountErrorResult errorResult;
}
