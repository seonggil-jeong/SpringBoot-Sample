package com.example.spring.app.controller;

import com.example.spring.app.controller.service.AuthAccountService;
import com.example.spring.app.vo.LoginRequest;
import com.example.spring.app.vo.RegisterAccountRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static com.example.spring.app.constants.AccountConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth")
public class AuthController {
    private final AuthAccountService accountService;

    @Operation(summary = "사용자 로그인", description = "로그인 성공 시 토큰 발급 {TOKEN : value}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "비밀번호 확인"),
            @ApiResponse(responseCode = "404", description = "일치하는 사용자 정보를 찾을 수 없음")
    })
    @PostMapping("/accounts/login")
    public ResponseEntity<String> userLogin(@Validated @RequestBody LoginRequest request) throws Exception {
        final String token = accountService.login(request);
        HttpHeaders headers = new HttpHeaders();

        headers.add("TOKEN", token);

        return ResponseEntity.ok().headers(headers).build();
    }


    @Operation(summary = "사용자 회원가입", description = "사용자 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "사용자 회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "이름, 닉네임 중복")
    })
    @PostMapping("/accounts/join")
    public ResponseEntity<Void> createAccount(@Validated @RequestBody RegisterAccountRequest request)
            throws Exception {

        accountService.registerAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
