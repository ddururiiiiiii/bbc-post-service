package com.bookbookclub.bbc_post_service.feed.exception;


import lombok.Getter;

/**
 * 존재하지 않는 피드에 접근할 때 발생
 */
@Getter
public class FeedNotFoundException extends RuntimeException {
    private final FeedErrorCode errorCode;

    public FeedNotFoundException() {
        super(FeedErrorCode.FEED_NOT_FOUND.getMessage());
        this.errorCode = FeedErrorCode.FEED_NOT_FOUND;
    }
}
