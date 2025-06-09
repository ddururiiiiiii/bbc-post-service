package com.bookbookclub.bbc_post_service.like.exception;


import com.bookbookclub.bbc_post_service.global.exception.ErrorCode;
import lombok.Getter;

/**
 * 좋아요 관련 비즈니스 예외
 */
@Getter
public class LikeException extends RuntimeException {

    private final ErrorCode errorCode;

    public LikeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
