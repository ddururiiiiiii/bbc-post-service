package com.bookbookclub.bbc_post_service.feed.exception;

import com.bookbookclub.common.exception.BusinessException;

/**
 * 블라인드 처리된 피드에 접근할 때 발생하는 예외
 */
public class FeedBlindedException extends BusinessException {
    public FeedBlindedException() {
        super(FeedErrorCode.FEED_BLINDED);
    }
}
