package com.bookbookclub.bbc_post_service.global.exception;


import com.bookbookclub.bbc_post_service.book.exception.DuplicateIsbnException;
import com.bookbookclub.bbc_post_service.feed.exception.FeedBlindedException;
import com.bookbookclub.bbc_post_service.feed.exception.FeedNotFoundException;
import com.bookbookclub.bbc_post_service.global.common.ApiResponse;
import com.bookbookclub.bbc_post_service.like.exception.LikeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 핸들러
 * - 각 예외 상황에 맞는 HTTP 상태코드 및 응답 구조 반환
 * - ApiResponse.fail(...) 구조 사용
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeedNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleFeedNotFound(FeedNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(e.getErrorCode()));
    }

    @ExceptionHandler(LikeException.class)
    public ResponseEntity<ApiResponse<Void>> handleLikeException(LikeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(e.getErrorCode()));
    }

    @ExceptionHandler(FeedBlindedException.class)
    public ResponseEntity<ApiResponse<Void>> handleFeedBlinded(FeedBlindedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.fail(e.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleAllException(Exception ex) {
        ex.printStackTrace(); // 운영환경에서는 로거로 변경
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateIsbn(DuplicateIsbnException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.fail(e.getErrorCode()));
    }


}
