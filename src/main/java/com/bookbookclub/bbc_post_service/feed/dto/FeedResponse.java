package com.bookbookclub.bbc_post_service.feed.dto;

import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.common.dto.UserSummaryResponse;

import java.time.LocalDateTime;

/**
 * 피드 응답 DTO + 유저 요약 정보 포함
 */
public record FeedResponse(
        Long id,
        Long userId,
        Long bookId,
        String content,
        String status,
        LocalDateTime createdAt,
        UserSummaryResponse user
) {
    public static FeedResponse from(Feed feed, UserSummaryResponse user) {
        return new FeedResponse(
                feed.getId(),
                feed.getUserId(),
                feed.getBookId(),
                feed.getContent(),
                feed.getStatus().name(),
                feed.getCreatedAt(),
                user
        );
    }
}
