package com.example.spring.security.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Builder
public class PasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String userId;
    private String role;

    public PasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);

    }
}
