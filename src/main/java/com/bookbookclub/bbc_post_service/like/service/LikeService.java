package com.bookbookclub.bbc_post_service.like.service;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import com.bookbookclub.bbc_post_service.feed.service.RankingFeedService;
import com.bookbookclub.bbc_post_service.global.kafka.PostEventProducer;
import com.bookbookclub.bbc_post_service.like.exception.LikeErrorCode;
import com.bookbookclub.common.dto.UserSummaryResponse;
import com.bookbookclub.bbc_post_service.like.entity.Like;
import com.bookbookclub.bbc_post_service.like.exception.LikeException;
import com.bookbookclub.bbc_post_service.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 좋아요(Like) 관련 비즈니스 로직 처리 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final FeedRepository feedRepository;
    private final RankingFeedService rankingFeedService;
    private final PostEventProducer postEventProducer;

    /**
     * 좋아요 토글 기능
     * - 이미 좋아요 되어 있으면 삭제 (취소)
     * - 좋아요 안 되어 있으면 생성
     *
     * @param userId 사용자 ID
     * @param feedId 피드 ID
     * @return 토글 후 상태 (true: 좋아요됨, false: 좋아요 취소됨)
     */
    @Transactional
    public boolean toggleLike(Long userId, Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new LikeException(LikeErrorCode.LIKE_NOT_FOUND));

        return likeRepository.findByUserIdAndFeedId(userId, feedId)
                .map(existingLike -> {
                    likeRepository.delete(existingLike);
                    rankingFeedService.incrementLike("weekly", feedId, -1);
                    return false;
                })
                .orElseGet(() -> {
                    likeRepository.save(Like.create(userId, feed));  // userId만 전달
                    rankingFeedService.incrementLike("weekly", feedId, 1);

                    postEventProducer.sendLikeEvent(feedId, userId);
                    return true;
                });
    }

    /**
     * 피드의 좋아요 수 조회
     */
    public long getLikeCount(Long feedId) {
        return likeRepository.countByFeedId(feedId);
    }

    /**
     * 사용자가 특정 피드에 좋아요를 눌렀는지 여부 조회
     */
    public boolean hasUserLiked(Long userId, Long feedId) {
        return likeRepository.existsByUserIdAndFeedId(userId, feedId);
    }

    /**
     * 특정 피드를 좋아요한 사용자 목록 조회
     * @param feedId 피드 ID
     * @return 해당 피드를 좋아요한 사용자들의 요약 정보 리스트
     */
    @Transactional(readOnly = true)
    public List<UserSummaryResponse> getUsersWhoLikedFeed(Long feedId) {
        return likeRepository.findAllByFeedId(feedId).stream()
                .map(like -> new UserSummaryResponse(like.getUserId(), null, null))
                .collect(Collectors.toList());
    }


    public List<Long> getLikedFeedIds(Long userId) {
        return likeRepository.findFeedIdsByUserId(userId);
    }
}
