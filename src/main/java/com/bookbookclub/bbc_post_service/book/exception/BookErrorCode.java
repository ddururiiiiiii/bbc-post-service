package com.bookbookclub.bbc_post_service.book.exception;

import com.bookbookclub.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Book 도메인 에러 코드 모음
 */
@Getter
public enum BookErrorCode implements BaseErrorCode {

    BOOK_NOT_FOUND(404, "BOOK_001", "책 정보를 찾을 수 없습니다."),
    DUPLICATE_ISBN(400, "BOOK_002", "이미 등록된 ISBN입니다.");

    private final int statusCode;
    private final String code;
    private final String message;

    BookErrorCode(int statusCode, String code, String message) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
    }

}
