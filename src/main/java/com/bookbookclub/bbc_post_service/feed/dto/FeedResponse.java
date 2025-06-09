package com.bookbookclub.bbc_post_service.feed.dto;

import com.bookbookclub.bbc_post_service.book.entity.Book;
import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.like.dto.UserSummaryResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 피드 응답 DTO
 */
@Getter
public class FeedResponse {

    private final Long id;
    private final Long userId;
    private final String nickname;
    private final String profileImageUrl;

    private final Long bookId;
    private final String bookTitle;
    private final String bookAuthor;
    private final String bookPublisher;
    private final String bookThumbnailUrl;

    private final String content;
    private final boolean isBlind;
    private final int likeCount;
    private final boolean liked;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    //통합 생성자
    public FeedResponse(Feed feed, Book book, UserSummaryResponse writer, int likeCount, boolean liked) {
        this.id = feed.getId();

        this.userId = writer.getId();
        this.nickname = writer.getNickname();
        this.profileImageUrl = writer.getProfileImageUrl();

        this.bookId = book.getId();
        this.bookTitle = book.getTitle();
        this.bookAuthor = book.getAuthor();
        this.bookPublisher = book.getPublisher();
        this.bookThumbnailUrl = book.getThumbnailUrl();

        this.content = feed.getContent();
        this.isBlind = feed.isBlinded();
        this.likeCount = likeCount;
        this.liked = liked;

        this.createdAt = feed.getCreatedAt();
        this.updatedAt = feed.getUpdatedAt();
    }
}
