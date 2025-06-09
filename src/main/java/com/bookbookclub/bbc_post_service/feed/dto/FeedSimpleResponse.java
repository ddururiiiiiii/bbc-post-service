package com.bookbookclub.bbc_post_service.feed.dto;

import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FeedSimpleResponse {

    private final Long id;
    private final Long userId;
    private final Long bookId;
    private final String content;
    private final LocalDateTime updatedAt;

    public static FeedSimpleResponse from(Feed feed) {
        return new FeedSimpleResponse(
                feed.getId(),
                feed.getUserId(),
                feed.getBookId(),
                feed.getContent(),
                feed.getUpdatedAt()
        );
    }
}
