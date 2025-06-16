package com.bookbookclub.bbc_post_service.feed.entity;

import com.bookbookclub.bbc_post_service.book.entity.Book;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 피드(게시글) 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    /** 책 ID */
    @Column(nullable = false)
    private Long bookId;

    /** 피드 본문 내용 */
    @Column(nullable = false, length = 1000)
    private String content;

    /** 피드 상태 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeedStatus status = FeedStatus.ACTIVE;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 피드 내용 수정
     */
    public void updateContent(String content) {
        this.content = content;
    }

    /**
     * 피드 블라인드 처리
     */
    public void blind() {
        this.status = FeedStatus.BLINDED;
    }

    /**
     * 피드 삭제 처리 (논리삭제)
     */
    public void delete() {
        this.status = FeedStatus.DELETED;
    }

    /**
     * 피드 복구 처리
     * @return
     */
    public boolean isActive() {
        return this.status == FeedStatus.ACTIVE;
    }
    /**
     * 피드 생성 메서드
     */
    public static Feed create(Long userId, Long bookId, String content) {
        Feed feed = new Feed();
        feed.userId = userId;
        feed.bookId = bookId;
        feed.content = content;
        feed.status = FeedStatus.ACTIVE;
        return feed;
    }

}
