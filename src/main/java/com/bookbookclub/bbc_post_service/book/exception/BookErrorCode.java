package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.exception.BaseErrorCode;

/**
 * 도서 도메인 에러 코드 정의
 */
public enum BookErrorCode implements BaseErrorCode {

    NOT_FOUND_BOOK(404, "B001", "등록된 책을 찾을 수 없습니다."),
    DUPLICATE_ISBN(409, "B002", "이미 등록된 ISBN입니다.");
    ;

    private final int statusCode;
    private final String code;
    private final String message;

    BookErrorCode(int statusCode, String code, String message) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
