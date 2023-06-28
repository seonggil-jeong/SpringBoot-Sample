package com.example.spring.app.service.impl;

import com.example.spring.app.service.PostService;
import com.example.spring.exception.PostException;
import com.example.spring.exception.result.PostErrorResult;
import com.example.spring.app.repository.PostRepository;
import com.example.spring.app.repository.model.PostEntity;
import com.example.spring.app.service.PrivateAccountService;
import com.example.spring.app.service.PrivateCommentService;
import com.example.spring.app.service.PrivatePostService;
import com.example.spring.app.vo.CreatePostRequest;
import com.example.spring.app.vo.PostDetailResponse;
import com.example.spring.app.vo.PostInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService, PrivatePostService {
    private final PostRepository postRepository;
    private final PrivateAccountService accountService;
    private final PrivateCommentService commentService;

    /**
     * ------------------------------------- for PostService -------------------------------------
     */

    /**
     * CreatePost
     *
     * @param request {postTitle, postContent, userId}
     *                -> autoSet {createDate}
     * @param userId
     * @throws Exception Account Not Found
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createPost(final CreatePostRequest request, final String userId) throws Exception {

        postRepository.save(PostEntity.builder()
                .postContent(request.getPostContent())
                .postTitle(request.getPostTitle())
                .createDate(new Date())
                .createUserId(accountService.getAccountEntityById(userId)).build());

    }

    /**
     * get PostList
     *
     * @param pageRequest {page, size} default Values{0, 10}
     * @return {pageCount, maxPageCount, PostInfo List}
     * @throws Exception
     */
    @Override
    public PostInfoResponse findPostList(PageRequest pageRequest) throws Exception {

        final Page<PostEntity> postEntities = postRepository.findAllByOrderByCreateDateDesc(pageRequest);

        return PostInfoResponse.of(postEntities);
    }

    /**
     * find PostDetail By PostSeq
     *
     * @param postSeq
     * @return
     * @throws Exception Post Not Found
     */
    @Override
    public PostDetailResponse findPostDetail(long postSeq) throws Exception {
        final PostEntity post = postRepository.findById(postSeq).orElseThrow(()
                -> new PostException(PostErrorResult.POST_NOT_FOUND));

        return PostDetailResponse.of(post, commentService.findCommentByPostSeq(post.getPostSeq()));
    }

    /**
     * ------------------------------------- for PrivatePostService -------------------------------------
     */

    @Override
    public PostEntity getPostEntityByPostSeq(long postSeq) throws Exception {
        return postRepository.findById(postSeq).orElseThrow(()
                -> new PostException(PostErrorResult.POST_NOT_FOUND));
    }
}
