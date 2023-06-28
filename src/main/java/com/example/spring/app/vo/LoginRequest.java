package com.example.spring.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Schema(description = "로그인")
public class LoginRequest {
    @NotNull(message = "userId cannot be null")
    @Schema(description = "사용자 아이디", example = "userId", required = true)
    private final String userId;
    @NotNull(message = "password cannot be null")
    @Schema(description = "사용자 비밀번호", example = "password", required = true)
    private final String password;
}
