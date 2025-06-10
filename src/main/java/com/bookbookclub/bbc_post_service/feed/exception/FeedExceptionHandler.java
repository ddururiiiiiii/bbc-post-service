package com.bookbookclub.bbc_post_service.feed.exception;

import com.bookbookclub.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Feed 도메인 예외 핸들러
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.bookbookclub.bbc_post_service.feed")
public class FeedExceptionHandler {

    /**
     * 피드가 존재하지 않는 경우 예외 처리
     */
    @ExceptionHandler(FeedNotFoundException.class)
    public ApiResponse<?> handleFeedNotFoundException(FeedNotFoundException e) {
        log.warn("FeedNotFoundException: {}", e.getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }

    /**
     * 블라인드 처리된 피드 접근 시 예외 처리
     */
    @ExceptionHandler(FeedBlindedException.class)
    public ApiResponse<?> handleFeedBlindedException(FeedBlindedException e) {
        log.warn("FeedBlindedException: {}", e.getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }
}
