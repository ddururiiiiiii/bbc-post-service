package com.bookbookclub.bbc_post_service.like.exception;

import com.bookbookclub.common.exception.BaseErrorCode;
import lombok.Getter;

@Getter
public enum LikeErrorCode implements BaseErrorCode {



    ALREADY_LIKED("LIKE_ALREADY_EXISTS", "이미 좋아요를 눌렀습니다."),
    LIKE_NOT_FOUND("LIKE_NOT_FOUND", "좋아요 내역을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    LikeErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
