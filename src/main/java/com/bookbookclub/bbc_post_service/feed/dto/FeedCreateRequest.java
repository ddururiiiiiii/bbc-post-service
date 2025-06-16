package com.bookbookclub.bbc_post_service.feed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 피드 생성 요청 DTO
 */
public record FeedCreateRequest(

        /** 작성자 ID */
        @NotNull(message = "userId는 필수입니다.")
        Long userId,

        /** 책 ID */
        @NotNull(message = "bookId는 필수입니다.")
        Long bookId,

        /** 피드 내용 */
        @NotBlank(message = "내용은 공백일 수 없습니다.")
        String content

) {}
