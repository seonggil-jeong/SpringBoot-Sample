package com.example.spring.app.vo;

import com.example.spring.app.repository.model.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
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
