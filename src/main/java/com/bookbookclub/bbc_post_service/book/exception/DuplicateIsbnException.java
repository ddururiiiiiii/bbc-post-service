package com.bookbookclub.bbc_post_service.book.exception;


import lombok.Getter;

/**
 * 중복 ISBN 예외
 * - 책 등록 시 중복 ISBN 검사에 사용
 */
@Getter
public class DuplicateIsbnException extends RuntimeException {
    private final BookErrorCode errorCode;

    public DuplicateIsbnException() {
        super(BookErrorCode.DUPLICATE_ISBN.getMessage());
        this.errorCode = BookErrorCode.DUPLICATE_ISBN;
    }
}
