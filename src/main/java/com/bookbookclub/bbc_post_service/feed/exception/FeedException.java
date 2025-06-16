package com.bookbookclub.bbc_post_service.feed.exception;

import com.bookbookclub.common.exception.BusinessException;

/**
 * 피드 도메인의 비즈니스 예외 처리
 */
public class FeedException extends BusinessException {
    public FeedException(FeedErrorCode errorCode) {
        super(errorCode);
    }
}
