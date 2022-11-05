package com.example.spring.services;

import com.example.spring.vo.RegisterAccountRequest;

/**
 * use in Service layer
 */
public interface PrivateValidateService {
    void canRegister(RegisterAccountRequest request) throws Exception;
}
