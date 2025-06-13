package com.bookbookclub.bbc_post_service.feed.exception;

import com.bookbookclub.common.exception.BaseErrorCode;

/**
 * 피드 도메인 에러 코드 정의
 */
public enum FeedErrorCode implements BaseErrorCode {

    FEED_NOT_FOUND(404, "F001", "피드를 찾을 수 없습니다."),
    FEED_BLINDED(403, "F002", "블라인드 처리된 피드입니다.");

    private final int statusCode;
    private final String code;
    private final String message;

    FeedErrorCode(int statusCode, String code, String message) {
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
