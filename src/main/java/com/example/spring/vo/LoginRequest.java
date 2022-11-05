package com.example.spring.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class LoginRequest {
    @NotNull(message = "userId cannot be null")
    private final String userId;
    @NotNull(message = "password cannot be null")
    private final String password;
}
