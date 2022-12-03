package com.example.spring.app.service;

import com.example.spring.app.vo.RegisterAccountRequest;

/**
 * use in Service layer
 */
public interface PrivateValidateService {
    void canRegister(RegisterAccountRequest request) throws Exception;
}
