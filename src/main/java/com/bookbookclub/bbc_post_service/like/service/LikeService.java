package com.bookbookclub.bbc_post_service.like.service;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.exception.FeedErrorCode;
import com.bookbookclub.bbc_post_service.feed.exception.FeedException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import com.bookbookclub.bbc_post_service.global.kafka.LikeEventProducer;
import com.bookbookclub.bbc_post_service.like.exception.LikeErrorCode;
import com.bookbookclub.bbc_post_service.like.entity.Like;
import com.bookbookclub.bbc_post_service.like.exception.LikeException;
import com.bookbookclub.bbc_post_service.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 좋아요(Like) 관련 비즈니스 로직 처리 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final FeedRepository feedRepository;
    private final LikeEventProducer likeEventProducer;

    /**
     * 좋아요 등록
     */
    @Transactional
    public void like(Long userId, Long feedId) {
        validateDuplicateLike(userId, feedId);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new FeedException(FeedErrorCode.FEED_NOT_FOUND));
        Long receiverUserId = feed.getUserId();

        Like like = Like.of(userId, feedId);
        likeRepository.save(like);

        // Kafka 이벤트 발행
        likeEventProducer.sendLikeCreated(feedId, userId, receiverUserId);
    }
    /**
     * 좋아요 취소
     */
    @Transactional
    public void unlike(Long userId, Long feedId) {
        Like like = likeRepository.findByUserIdAndFeedId(userId, feedId)
                .orElseThrow(() -> new LikeException(LikeErrorCode.LIKE_NOT_FOUND));
        likeRepository.delete(like);
    }

    /**
     * 좋아요 수 조회
     */
    public long countLikes(Long feedId) {
        return likeRepository.countByFeedId(feedId);
    }

    /**
     * 중복 좋아요 방지
     */
    private void validateDuplicateLike(Long userId, Long feedId) {
        likeRepository.findByUserIdAndFeedId(userId, feedId)
                .ifPresent(like -> {
                    throw new LikeException(LikeErrorCode.ALREADY_LIKED);
                });
    }


}
