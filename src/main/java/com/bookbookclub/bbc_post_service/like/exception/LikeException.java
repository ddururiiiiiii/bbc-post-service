package com.bookbookclub.bbc_post_service.like.exception;

import lombok.Getter;

/**
 * 좋아요 관련 비즈니스 예외
 */
@Getter
public class LikeException extends RuntimeException {

    private final LikeErrorCode errorCode;

    public LikeException(LikeErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}