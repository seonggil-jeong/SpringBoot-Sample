package com.example.spring.services;

import com.example.spring.vo.LoginRequest;
import com.example.spring.vo.RegisterAccountRequest;

public interface AuthAccountService {

    void registerAccount(RegisterAccountRequest request) throws Exception;

    String login(LoginRequest request) throws Exception;

}
