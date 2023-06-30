package com.example.spring.app.controller;

import com.example.spring.app.controller.support.ControllerSupport;
import com.example.spring.app.service.AdminPostService;
import com.example.spring.app.service.PostService;
import com.example.spring.app.vo.CreatePostRequest;
import com.example.spring.app.vo.PostDetailResponse;
import com.example.spring.app.vo.PostInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Post")
public class PostController extends ControllerSupport {
    private final PostService postService;
    private final AdminPostService adminPostService;

    @Operation(summary = "Post 등록", description = "Post 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "Validated Error")
    })
    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody @Validated final CreatePostRequest request)
            throws Exception {

        postService.createPost(request, super.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    @Operation(summary = "Post 목록 조회", description = "Post 목록 조회 (page, size)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/posts")
    public ResponseEntity<PostInfoResponse> findPosts(
            @Parameter(description = "조회할 post 수", example = "10")
            @RequestParam(value = "size", required = false, defaultValue = "10") final Integer size,
            @Parameter(description = "page 번호", example = "0")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) throws Exception {

        return ResponseEntity.ok().body(postService.findPostList(PageRequest.of(page, size)));
    }

    @Operation(summary = "Post 상세 조회", description = "Post 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 Post 정보를 찾을 수 없음")
    })
    @GetMapping("/posts/{postSeq}")
    public ResponseEntity<PostDetailResponse> findPostDetail(
            @PathVariable final long postSeq) throws Exception {


        return ResponseEntity.ok().body(postService.findPostDetail(postSeq));

    }

    @Operation(summary = "Post 삭제 By Admin", description = "Post 삭제 with Admin Role")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "삭제할 Post를 찾을 수 없음")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/posts/{postSeq}")
    public ResponseEntity<Void> deletePostByPostSeq(
            @PathVariable final long postSeq
    ) throws Exception {
        adminPostService.deletePostByPostSeq(postSeq);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
