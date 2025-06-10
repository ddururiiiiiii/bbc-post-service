package com.bookbookclub.bbc_post_service.like.exception;

import com.bookbookclub.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 좋아요 도메인 예외 핸들러
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.bookbookclub.bbc_post_service.like")
public class LikeExceptionHandler {

    @ExceptionHandler(LikeException.class)
    public ApiResponse<?> handleLikeException(LikeException e) {
        log.warn("LikeException: {}", e.getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }
}