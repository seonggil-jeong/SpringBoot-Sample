package com.example.spring.controller;

import com.example.spring.object.ImmutabilityList;
import com.example.spring.security.impl.JwtAuthTokenProvider;
import com.example.spring.controller.service.PostService;
import com.example.spring.vo.CreatePostRequest;
import com.example.spring.vo.PostDetailResponse;
import com.example.spring.vo.PostInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.spring.constants.AccountConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;
    private final JwtAuthTokenProvider provider;

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(
            @RequestHeader(AUTHORIZATION_TOKEN_KEY) final String token, @RequestBody @Validated final CreatePostRequest request)
            throws Exception {

        postService.createPost(request, provider.getUserId(token));

        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    @GetMapping("/posts")
    public ResponseEntity<PostInfoResponse> findPosts(
            @RequestParam(value = "size", required = false, defaultValue = "10") final Integer size,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) throws Exception {

        return ResponseEntity.ok().body(postService.findPostList(PageRequest.of(page, size)));
    }

    @GetMapping("/posts/{postSeq}")
    public ResponseEntity<PostDetailResponse> findPostDetail(@PathVariable final long postSeq) throws Exception {

        return ResponseEntity.ok().body(postService.findPostDetail(postSeq));

    }
}
