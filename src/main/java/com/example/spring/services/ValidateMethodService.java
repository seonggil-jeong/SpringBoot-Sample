package com.example.spring.services;

import com.example.spring.vo.RegisterAccountRequest;

public interface ValidateMethodService {
    void canRegister(RegisterAccountRequest request) throws Exception;
}
