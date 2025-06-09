package com.bookbookclub.bbc_post_service.feed.entity;

import com.bookbookclub.bbc_post_service.book.entity.Book;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 피드(Feed) 엔티티
 * - 사용자가 작성한 게시글 정보를 저장
 * - 작성자(User)와 다대일 관계
 * - 생성일, 수정일 자동 관리
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

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private boolean isBlinded = false;

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
        this.isBlinded = true;
    }

    /**
     * 피드 생성 메서드
     */
    public static Feed create(Long userId, Long bookId, String content) {
        Feed feed = new Feed();
        feed.userId = userId;
        feed.bookId = bookId;
        feed.content = content;
        feed.isBlinded = false;
        return feed;
    }

}
