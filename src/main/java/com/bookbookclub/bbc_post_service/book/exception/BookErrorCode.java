package com.bookbookclub.common.exception.book;

import com.bookbookclub.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Book 도메인 에러 코드 모음
 */
@Getter
@AllArgsConstructor
public enum BookErrorCode implements BaseErrorCode {

    BOOK_NOT_FOUND(404, "BOOK_001", "책 정보를 찾을 수 없습니다."),
    DUPLICATE_ISBN(400, "BOOK_002", "이미 등록된 ISBN입니다.");

    private final int statusCode;
    private final String code;
    private final String message;
}
