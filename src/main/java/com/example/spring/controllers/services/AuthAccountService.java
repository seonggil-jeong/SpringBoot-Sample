package com.example.spring.controllers.services;

import com.example.spring.vo.LoginRequest;
import com.example.spring.vo.RegisterAccountRequest;
/**
 * use in Controller layer
 */
public interface AuthAccountService {

    void registerAccount(RegisterAccountRequest request) throws Exception;

    String login(LoginRequest request) throws Exception;

}
