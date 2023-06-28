package com.example.spring.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Schema(description = "Comment 생성")
public class CreateCommentRequest {
    @NotNull(message = "content cannot be null")
    @Schema(description = "내용", example = "content", required = true)
    private final String content;
}
