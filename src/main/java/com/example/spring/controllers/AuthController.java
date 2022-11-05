package com.example.spring.controllers;

import com.example.spring.services.AuthAccountService;
import com.example.spring.vo.LoginRequest;
import com.example.spring.vo.RegisterAccountRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.spring.constants.AccountConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthAccountService accountService;

    @PostMapping("/account/login")
    public ResponseEntity<String> userLogin(@Validated @RequestBody LoginRequest request) throws Exception {
        final String token = accountService.login(request);
        HttpHeaders headers = new HttpHeaders();

        headers.add(AUTHORIZATION_TOKEN_KEY, token);

        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/account/join")
    public ResponseEntity<Void> createAccount(@Validated @RequestBody RegisterAccountRequest request)
            throws Exception {

        accountService.registerAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
