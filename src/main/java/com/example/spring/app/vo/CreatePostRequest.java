package com.example.spring.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Schema(description = "Post 등록")
public class CreatePostRequest {
    @NotNull(message = "postTitle cannot be null")
    @Schema(description = "Post Title", example = "postTitle", required = true)
    private final String postTitle;

    @NotNull(message = "postContent cannot be null")
    @Schema(description = "post Content", example = "post content" ,required = true)
    private final String postContent;
}
