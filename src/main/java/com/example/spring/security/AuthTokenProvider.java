package com.example.spring.security;


import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Map;

public interface AuthTokenProvider<T> {
    T createToken(String id, String role, Map<String, Object> claims, Date expiredDate);

    T convertAuthToken(String token);

    Authentication getAuthentication(T authToken);
}
