package com.example.spring.security.impl;

import com.example.spring.exception.AccountException;
import com.example.spring.exception.result.AccountErrorResult;
import com.example.spring.repository.AccountRepository;
import com.example.spring.repository.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class PasswordAuthAuthenticationsManager implements AuthenticationProvider {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountEntity account = accountRepository.findById(authentication.getPrincipal().toString()).orElseThrow(()
                -> new AccountException(AccountErrorResult.ACCOUNT_NOT_FOUND));

        if (account.getState().equals(0)) {
            throw new AccountException(AccountErrorResult.DELETED_ACCOUNT);
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            throw new AccountException(AccountErrorResult.PASSWORD_NOT_MATCHED);
        }

        return new PasswordAuthenticationToken(account.getUserId(), account.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(account.getRole().getRole())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PasswordAuthenticationToken.class);
    }
}
