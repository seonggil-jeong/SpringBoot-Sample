package com.example.spring.app.vo;

import com.example.spring.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Schema(description = "사용자 회원가입")
public class RegisterAccountRequest {
    @NotNull(message = "userId cannot be null")
    @Schema(description = "사용자 아이디", example = "userId", required = true)
    private final String userId;

    @NotNull(message = "password cannot be null")
    @Schema(description = "사용자 비밀번호", example = "password", required = true)
    private final String password;

    @NotNull(message = "userName cannot be null")
    @Schema(description = "사용자 이름", example = "userName", required = true)
    private final String userName;

    @NotNull(message = "nickname cannot be null")
    @Schema(description = "사용자 닉네임", example = "nickname", required = true)
    private final String nickname;

}
