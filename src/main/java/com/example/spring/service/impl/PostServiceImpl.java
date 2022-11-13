package com.example.spring.service.impl;

import com.example.spring.exception.PostException;
import com.example.spring.exception.result.PostErrorResult;
import com.example.spring.repository.PostRepository;
import com.example.spring.repository.entity.PostEntity;
import com.example.spring.controller.service.PostService;
import com.example.spring.service.PrivateAccountService;
import com.example.spring.service.PrivateCommentService;
import com.example.spring.service.PrivatePostService;
import com.example.spring.vo.CreatePostRequest;
import com.example.spring.vo.PostDetailResponse;
import com.example.spring.vo.PostInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        List<PostInfoResponse.PostInfo> postInfos = new LinkedList<>(); // postInfo List

        final Page<PostEntity> postEntities = postRepository.findAllByOrderByCreateDateDesc(pageRequest);

        postEntities.forEach(postEntity -> { // set PostInfo
            postInfos.add(PostInfoResponse.PostInfo.builder()
                    .postSeq(postEntity.getPostSeq())
                    .postTitle(postEntity.getPostTitle())
                    .createUserId(postEntity.getCreateUserId().getUserId())
                    .createDate(postEntity.getCreateDate()).build());
        });

        return PostInfoResponse.builder()
                .postCount(postEntities.getTotalElements()) // total posts count
                .maxPageCount(postEntities.getTotalPages()) // total posts / size (pageCount)
                .postInfos(postInfos).build();
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

        return PostDetailResponse.builder()
                .postSeq(post.getPostSeq())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .createDate(post.getCreateDate())
                .createUserId(post.getCreateUserId().getUserId())
                .comments(commentService.findCommentByPostSeq(postSeq)).build();
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
