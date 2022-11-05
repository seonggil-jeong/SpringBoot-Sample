package com.example.spring.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountConstants {
    public static final String ACCOUNT_HEADER = "X-ACCOUNT";
    public static final String AUTHORIZATION_TOKEN_KEY = "X-TOKEN";
}
