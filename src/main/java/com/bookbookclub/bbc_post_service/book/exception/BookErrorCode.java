package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.exception.BaseErrorCode;
import lombok.Getter;

@Getter
public enum BookErrorCode implements BaseErrorCode {

    DUPLICATE_ISBN("BOOK_DUPLICATE_ISBN", "이미 등록된 ISBN입니다.");

    private final String code;
    private final String message;

    BookErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}