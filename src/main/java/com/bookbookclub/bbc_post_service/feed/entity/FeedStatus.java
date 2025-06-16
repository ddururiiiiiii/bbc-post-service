package com.bookbookclub.bbc_post_service.feed.entity;

import lombok.Getter;

/**
 * 피드 상태 열거형
 */
@Getter
public enum FeedStatus {
    ACTIVE("정상"),
    BLINDED("블라인드"),
    DELETED("삭제");

    private final String description;

    FeedStatus(String description) {
        this.description = description;
    }
}