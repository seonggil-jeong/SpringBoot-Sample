package com.example.spring.exceptions;

import com.example.spring.exceptions.results.AccountErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountException extends RuntimeException {


    private final AccountErrorResult errorResult;
}
