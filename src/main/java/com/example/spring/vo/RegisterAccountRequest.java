package com.example.spring.vo;

import com.example.spring.enums.RoleType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class RegisterAccountRequest {
    @NotNull(message = "userId cannot be null")
    private final String userId;

    @NotNull(message = "password cannot be null")
    private final String password;

    @NotNull(message = "userName cannot be null")
    private final String userName;

    @NotNull(message = "nickname cannot be null")
    private final String nickname;

}
