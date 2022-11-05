package com.example.spring.services;

public interface ValidateAccountService {
    boolean validateUserId(String userId) throws Exception;

    boolean validateNickname(String nickname) throws Exception;
}
