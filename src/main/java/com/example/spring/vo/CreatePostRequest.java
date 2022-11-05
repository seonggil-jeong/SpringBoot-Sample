package com.example.spring.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreatePostRequest {
    @NotNull(message = "postTitle cannot be null")
    private final String postTitle;

    @NotNull(message = "postContent cannot be null")
    private final String postContent;
}
