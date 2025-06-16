package com.bookbookclub.bbc_post_service.feed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 피드 수정 요청 DTO
 */
public record FeedUpdateRequest(

        @NotBlank(message = "피드 내용은 공백일 수 없습니다.")
        @Size(max = 1000, message = "피드 내용은 1000자 이내여야 합니다.")
        String content

) {}
