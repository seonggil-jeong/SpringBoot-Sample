package com.example.spring.vo;

import com.example.spring.repository.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostDetailResponse {
    private final Long postSeq;
    private final String postTitle;
    private final String postContent;
    private final Date createDate;
    private final String createUserId;

    private final List<Comment> comments;
}
