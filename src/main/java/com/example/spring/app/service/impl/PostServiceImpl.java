package com.example.spring.app.service.impl;

import com.example.spring.app.repository.PostRepository;
import com.example.spring.app.repository.model.PostEntity;
import com.example.spring.app.service.*;
import com.example.spring.app.vo.CreatePostRequest;
import com.example.spring.app.vo.PostDetailResponse;
import com.example.spring.app.vo.PostInfoResponse;
import com.example.spring.exception.PostException;
import com.example.spring.exception.result.PostErrorResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.rmi.AccessException;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService, PrivatePostService, AdminPostService {
    private final PostRepository postRepository;
    private final PrivateAccountService accountService;
    private final PrivateCommentService commentService;

    /**
     * ------------------------------------- for AdminPostService -------------------------------------
     */

    @Override
    public void deletePostByPostSeq(long postSeq) {

        postRepository.delete(postRepository.findById(postSeq).orElseThrow(()
                -> new PostException(PostErrorResult.POST_NOT_FOUND)));

    }


    /**
     * ------------------------------------- for PostService -------------------------------------
     */


    @Override
    public void deletePostByPostSeq(String userId, long postSeq) throws Exception {

        final PostEntity targetPostEntity = postRepository.findById(postSeq).orElseThrow(()
                -> new PostException(PostErrorResult.POST_NOT_FOUND));

        if (!this.canAccessPost(postSeq, userId)) {
            throw new AccessException("cannot access this Resource");

        }

        postRepository.delete(targetPostEntity);
    }

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

    private boolean canAccessPost(final long postSeq, final String userId) throws Exception {
        return postRepository.findById(postSeq).orElseThrow(()
                        -> new PostException(PostErrorResult.POST_NOT_FOUND))
                .getCreateUserId().getUserId().equals(userId);

    }
}
