package com.bookbookclub.bbc_post_service.like.exception;

import com.bookbookclub.common.exception.BusinessException;

/**
 * 좋아요 도메인에서 발생하는 비즈니스 예외
 */
public class LikeException extends BusinessException {
    public LikeException(LikeErrorCode errorCode) {
        super(errorCode);
    }
}
