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
 * - 사용자가 특정 피드에 좋아요를 누른 기록을 저장
 * - 사용자(User)와 피드(Feed)와 다대일 관계
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "feed_id"})
})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 좋아요를 누른 사용자 ID */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 좋아요를 누른 피드 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    /** 좋아요 누른 시간 */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 좋아요 생성 정적 메서드
     */
    public static Like create(Long userId, Feed feed) {
        Like like = new Like();
        like.userId = userId;
        like.feed = feed;
        return like;
    }
}
