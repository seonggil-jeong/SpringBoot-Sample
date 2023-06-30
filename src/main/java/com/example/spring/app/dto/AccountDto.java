package com.example.spring.app.dto;

import com.example.spring.app.repository.model.AccountEntity;
import com.example.spring.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
public class AccountDto {
    private final String userId;
    private final String password;
    private final String userName;
    private final String nickname;
    private final RoleType role;
    private final Integer state;
    private final Date createDate;

    public static AccountDto ofEntity(AccountEntity entity) {
        return AccountDto.builder()
                .userId(entity.getUserId())
                .password(entity.getPassword())
                .userName(entity.getUserName())
                .nickname(entity.getNickname())
                .role(entity.getRole())
                .state(entity.getState())
                .createDate(entity.getCreateDate())
                .build();
    }
}
