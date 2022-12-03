package com.example.spring.app.controller.service;

import com.example.spring.app.vo.LoginRequest;
import com.example.spring.app.vo.RegisterAccountRequest;
/**
 * use in Controller layer
 */
public interface AuthAccountService {

    void registerAccount(RegisterAccountRequest request) throws Exception;

    String login(LoginRequest request) throws Exception;

}
