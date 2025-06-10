package com.bookbookclub.bbc_post_service.feed.exception;


import com.bookbookclub.bbc_post_service.book.exception.BookErrorCode;
import lombok.Getter;

/**
 * 블라인드 처리된 피드 접근 시 발생하는 예외
 */
@Getter
public class FeedBlindedException extends RuntimeException {
    private final FeedErrorCode errorCode;

    public FeedBlindedException() {
        super(FeedErrorCode.FEED_BLINDED.getMessage());
        this.errorCode = FeedErrorCode.FEED_BLINDED;
    }
}
