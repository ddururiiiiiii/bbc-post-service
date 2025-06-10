package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Book 도메인 예외 핸들러
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.bookbookclub.bbc_post_service.book")
public class BookExceptionHandler {

    /**
     * 중복 ISBN 예외 처리
     */
    @ExceptionHandler(DuplicateIsbnException.class)
    public ApiResponse<?> handleDuplicateIsbnException(DuplicateIsbnException e) {
        log.warn("DuplicateIsbnException: {}", e.getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }

}