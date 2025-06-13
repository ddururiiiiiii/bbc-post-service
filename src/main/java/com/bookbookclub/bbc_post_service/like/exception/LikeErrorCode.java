package com.bookbookclub.bbc_post_service.like.exception;

import com.bookbookclub.common.exception.BaseErrorCode;

/**
 * 좋아요 도메인 에러 코드 정의
 */
public enum LikeErrorCode implements BaseErrorCode {

    ALREADY_LIKED(400, "L001", "이미 좋아요를 눌렀습니다."),
    LIKE_NOT_FOUND(404, "L002", "좋아요 내역을 찾을 수 없습니다.");

    private final int statusCode;
    private final String code;
    private final String message;

    LikeErrorCode(int statusCode, String code, String message) {
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
