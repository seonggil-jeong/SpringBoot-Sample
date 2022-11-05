package com.example.spring.controllers.services;

import com.example.spring.vo.CreatePostRequest;
import com.example.spring.vo.PostDetailResponse;
import com.example.spring.vo.PostInfoResponse;
import org.springframework.data.domain.PageRequest;

/**
 * use in Controller layer
 */
public interface PostService {

    void createPost(CreatePostRequest request, String userId) throws Exception;

    PostInfoResponse findPostList(PageRequest pageRequest) throws Exception;

    PostDetailResponse findPostDetail(long postSeq) throws Exception;


}
