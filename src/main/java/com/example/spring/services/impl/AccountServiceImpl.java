package com.example.spring.services.impl;

import com.example.spring.enums.RoleType;
import com.example.spring.repositories.AccountRepository;
import com.example.spring.repositories.entities.AccountEntity;
import com.example.spring.security.JwtAuthToken;
import com.example.spring.security.impl.JwtAuthTokenProvider;
import com.example.spring.security.impl.PasswordAuthenticationToken;
import com.example.spring.services.AuthAccountService;
import com.example.spring.services.ValidateMethodService;
import com.example.spring.vo.LoginRequest;
import com.example.spring.vo.RegisterAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AuthAccountService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthTokenProvider tokenProvider;
    private final AccountRepository accountRepository;
    private final ValidateMethodService validateService;

    @Override
    public void registerAccount(final RegisterAccountRequest request) throws Exception {
        validateService.canRegister(request);

        accountRepository.save(AccountEntity.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .userName(request.getUserName())
                .createDate(new Date())
                .role(RoleType.USER)
                .state(1).build());

    }

    @Override
    public String login(LoginRequest request) throws Exception {
        PasswordAuthenticationToken token = new PasswordAuthenticationToken(request.getUserId(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return createToken((PasswordAuthenticationToken) authentication);
    }

    private String createToken(final PasswordAuthenticationToken token) {
        Date expiredDate = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", token.getUserId());
        claims.put("role", token.getRole());


        JwtAuthToken jwtAuthToken = tokenProvider.createToken(
                token.getPrincipal().toString(),
                token.getAuthorities().iterator().next().getAuthority(),
                claims,
                expiredDate
        );

        return jwtAuthToken.getToken();
    }
}
