package com.bookbookclub.bbc_post_service.book.exception;


import com.bookbookclub.bbc_post_service.global.exception.ErrorCode;
import lombok.Getter;

/**
 * 중복 ISBN 예외
 * - 책 등록 시 중복 ISBN 검사에 사용
 */
@Getter
public class DuplicateIsbnException extends RuntimeException {
    private final ErrorCode errorCode;

    public DuplicateIsbnException() {
        super(ErrorCode.DUPLICATE_ISBN.getMessage());
        this.errorCode = ErrorCode.DUPLICATE_ISBN;
    }
}
