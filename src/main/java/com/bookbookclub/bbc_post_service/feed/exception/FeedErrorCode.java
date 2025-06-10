package com.bookbookclub.bbc_post_service.feed.exception;

import com.bookbookclub.bbc_post_service.like.exception.LikeException;
import com.bookbookclub.common.exception.BaseErrorCode;
import lombok.Getter;

@Getter
public enum FeedErrorCode implements BaseErrorCode {

    FEED_NOT_FOUND("FEED_NOT_FOUND","피드를 찾을 수 없습니다."),
    FEED_BLINDED("FEED_BLINDED","블라인드 처리된 피드입니다.");

    private final String code;
    private final String message;

    FeedErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}