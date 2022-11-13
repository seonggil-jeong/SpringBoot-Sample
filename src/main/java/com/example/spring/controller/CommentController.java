package com.example.spring.controller;

import com.example.spring.controller.service.CommentService;
import com.example.spring.security.impl.JwtAuthTokenProvider;
import com.example.spring.vo.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.spring.constants.AccountConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentService commentService;
    private final JwtAuthTokenProvider provider;


    @PostMapping("/posts/{postSeq}/comments")
    public ResponseEntity<Void> createComment(
            @RequestHeader(AUTHORIZATION_TOKEN_KEY) final String token,
            @PathVariable final long postSeq,
            @RequestBody final CreateCommentRequest request) throws Exception {

        commentService.createComment(request, provider.getUserId(token), postSeq);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
