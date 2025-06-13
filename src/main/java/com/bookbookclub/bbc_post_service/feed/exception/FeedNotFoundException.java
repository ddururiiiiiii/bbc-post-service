package com.bookbookclub.bbc_post_service.feed.exception;

import com.bookbookclub.common.exception.BusinessException;

/**
 * 존재하지 않는 피드에 접근할 때 발생하는 예외
 */
public class FeedNotFoundException extends BusinessException {
    public FeedNotFoundException() {
        super(FeedErrorCode.FEED_NOT_FOUND);
    }
}
