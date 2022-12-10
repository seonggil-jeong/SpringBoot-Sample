package com.example.spring.app.vo;

import com.example.spring.app.repository.model.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class PostInfoResponse {
    private final long maxPageCount;
    private final long postCount;
    private final List<PostInfo> postInfos;


    public static PostInfoResponse of(Page<PostEntity> entities) {
        return PostInfoResponse.builder()
                .maxPageCount(entities.getTotalPages())
                .postCount(entities.getTotalElements())
                .postInfos(entities.map(postEntity -> {
                    return PostInfo.builder()
                            .postSeq(postEntity.getPostSeq())
                            .postTitle(postEntity.getPostTitle())
                            .createDate(postEntity.getCreateDate())
                            .createUserId(postEntity.getCreateUserId().getUserId()).build();
                }).toList()).build();


    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class PostInfo {
        private final Long postSeq;
        private final String postTitle;
        private final Date createDate;
        private final String createUserId;
    }

}
