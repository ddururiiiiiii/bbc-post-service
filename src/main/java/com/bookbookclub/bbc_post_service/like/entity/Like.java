package com.bookbookclub.bbc_post_service.like.entity;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 좋아요(Like) 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 좋아요를 누른 사용자 ID */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 좋아요를 누른 피드 ID (연관관계 제거) */
    @Column(name = "feed_id", nullable = false)
    private Long feedId;

    /** 생성 일시 */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 정적 팩토리 메서드
     */
    public static Like of(Long userId, Long feedId) {
        Like like = new Like();
        like.userId = userId;
        like.feedId = feedId;
        return like;
    }
}
