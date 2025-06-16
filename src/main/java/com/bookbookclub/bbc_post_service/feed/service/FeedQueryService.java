package com.bookbookclub.bbc_post_service.feed.service;

import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import com.bookbookclub.bbc_post_service.feed.exception.FeedErrorCode;
import com.bookbookclub.bbc_post_service.feed.exception.FeedException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 피드 단건 및 목록 조회를 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedQueryService {

    private final FeedRepository feedRepository;

    /**
     * 단건 피드 조회 (블라인드 제외)
     */
    public Feed getVisibleFeedById(Long feedId) {
        return feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)
                .orElseThrow(() -> new FeedException(FeedErrorCode.FEED_NOT_FOUND));
    }

    /**
     * 특정 유저의 피드 목록 조회 (블라인드 제외, 최신순 커서 기반)
     */
    public List<Feed> getVisibleFeedsByUser(Long lastFeedId, Long userId, int size) {
        return feedRepository.findFeedsByUserIdAndCursorWithoutBlinded(lastFeedId, userId, size);
    }

    /**
     * 전체 피드 목록 조회 (블라인드 제외, 최신순 커서 기반)
     */
    public List<Feed> getVisibleFeedsByCursor(Long lastFeedId, int size) {
        return feedRepository.findAllVisibleFeedsByCursor(lastFeedId, size);
    }
}

