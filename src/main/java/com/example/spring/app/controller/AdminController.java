package com.example.spring.app.controller;

import com.example.spring.app.dto.AccountDto;
import com.example.spring.app.service.AdminMangeAccountService;
import com.example.spring.app.service.AdminPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {
    private final AdminPostService adminPostService;
    private final AdminMangeAccountService adminAccountService;


    @GetMapping("/admin/users")
    public ResponseEntity<List<AccountDto>> findAllAccountList(
            @Parameter(description = "조회할 account 수", example = "10")
            @RequestParam(value = "size", required = false, defaultValue = "10") final Integer size,
            @Parameter(description = "page 번호", example = "0")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page
    ) throws Exception {

        return ResponseEntity.ok().body(adminAccountService.findAllAccount(PageRequest.of(page, size)));

    }


    @DeleteMapping("/admin/users/{userId}")
    public ResponseEntity<Void> deleteAccountByAdmin(
            @PathVariable final String userId
    )throws Exception {

        adminAccountService.deleteAccountByAdmin(userId);


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Post 삭제", description = "(with Admin role 포스트 삭제)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Post 삭제"),
            @ApiResponse(responseCode = "404", description = "cannot found target Post with Id")
    })
    @DeleteMapping("/admin/posts/{postSeq}")
    public ResponseEntity<Void> deletePostByAdmin(
            @PathVariable final long postSeq
    ) throws Exception {

        adminPostService.deletePostByPostSeq(postSeq);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
