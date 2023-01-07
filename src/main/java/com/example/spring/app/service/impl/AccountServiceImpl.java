package com.example.spring.app.service.impl;

import com.example.spring.app.event.publisher.AccountEventPublisher;
import com.example.spring.app.service.AuthAccountService;
import com.example.spring.app.service.PrivateValidateService;
import com.example.spring.enums.EventType;
import com.example.spring.enums.RoleType;
import com.example.spring.event.CreateAccountEvent;
import com.example.spring.event.LoginAccountEvent;
import com.example.spring.exception.AccountException;
import com.example.spring.exception.result.AccountErrorResult;
import com.example.spring.app.repository.AccountRepository;
import com.example.spring.app.repository.model.AccountEntity;
import com.example.spring.security.JwtAuthToken;
import com.example.spring.security.impl.JwtAuthTokenProvider;
import com.example.spring.security.impl.PasswordAuthenticationToken;
import com.example.spring.app.service.PrivateAccountService;
import com.example.spring.app.vo.LoginRequest;
import com.example.spring.app.vo.RegisterAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AuthAccountService, PrivateAccountService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthTokenProvider tokenProvider;
    private final AccountRepository accountRepository;
    private final PrivateValidateService validateService;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * ------------------------------------- for PrivateAccountService -------------------------------------
     */

    /**
     * getAccountEntity By userId
     *
     * @param userId
     * @return AccountEntity
     * @throws Exception Account Not Found (404)
     */
    @Override
    public AccountEntity getAccountEntityById(String userId) throws Exception {
        return accountRepository.findByUserIdAndState(userId, 1).orElseThrow(()
                -> new AccountException(AccountErrorResult.ACCOUNT_NOT_FOUND));
    }


    /**
     * ------------------------------------- for AuthAccountService -------------------------------------
     */


    /**
     * Register Account
     *
     * @param request {userId, nickname, password, username}
     *                autoSet {state : 1, role : USER, createDate : new Date}
     * @throws Exception
     */
    @Override
    public void registerAccount(final RegisterAccountRequest request) throws Exception {
        validateService.canRegister(request); // userId, nickname are duplicated

        final AccountEntity result = accountRepository.save(AccountEntity.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .userName(request.getUserName())
                .createDate(new Date())
                .role(RoleType.USER)
                .state(1).build());


        accountEventPublisher.pushCreateUserEvent(request.getUserId(), result.getUserName());
    }

    /**
     * User Login
     *
     * @param request {userId, password}
     * @return access Token
     * @throws Exception
     */
    @Override
    public String login(LoginRequest request) throws Exception {
        PasswordAuthenticationToken token = new PasswordAuthenticationToken(request.getUserId(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //push Login Event
        accountEventPublisher.pushLoginUserEvent(request.getUserId(), token.getRole());

        return this.createToken((PasswordAuthenticationToken) authentication);
    }

    /**
     * create Token with PasswordAuthenticationToken
     *
     * @param token
     * @return (String) token
     */
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
