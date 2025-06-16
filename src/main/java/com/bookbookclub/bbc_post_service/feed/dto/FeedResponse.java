package com.bookbookclub.bbc_post_service.feed.dto;

import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.common.dto.UserSummaryResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 피드 응답 DTO
 */
public record FeedResponse(
        Long id,
        Long userId,
        Long bookId,
        String content,
        String status,
        LocalDateTime createdAt
) {
    public static FeedResponse from(Feed feed) {
        return new FeedResponse(
                feed.getId(),
                feed.getUserId(),
                feed.getBookId(),
                feed.getContent(),
                feed.getStatus().name(),
                feed.getCreatedAt()
        );
    }
}
