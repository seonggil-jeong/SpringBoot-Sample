package com.example.spring.security;

public interface AuthToken  <T> {
    final static String AUTHORITIES_KEY = "role";

    boolean validate();

    T getData();
}
