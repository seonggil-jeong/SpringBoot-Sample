package com.example.spring.app.controller;

import com.example.spring.app.controller.service.CommentService;
import com.example.spring.app.controller.support.ControllerSupport;
import com.example.spring.security.impl.JwtAuthTokenProvider;
import com.example.spring.app.vo.CreateCommentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.spring.app.constants.AccountConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Comment")
public class CommentController extends ControllerSupport {
    private final CommentService commentService;


    @Operation(summary = "Comment 등록", description = "Comment 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패 Validated"),
            @ApiResponse(responseCode = "404", description = "Post 를 찾을 수 없음")
    })
    @PostMapping("/posts/{postSeq}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable final long postSeq, @Validated @RequestBody final CreateCommentRequest request) throws Exception {

        commentService.createComment(request, super.getUserId(), postSeq);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
