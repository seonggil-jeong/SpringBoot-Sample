package com.example.spring.exceptions.results;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AccountErrorResult {
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "cannot find Account"),
    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "password not matched"),
    DELETED_ACCOUNT(HttpStatus.BAD_REQUEST, "deleted account"),
    DUPLICATED_USER_ID(HttpStatus.BAD_REQUEST, "is Duplicated userId"),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "is Duplicated nickname"),


    ;


    private final HttpStatus status;
    private final String message;
}
