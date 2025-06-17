package com.bookbookclub.bbc_post_service.feed.service;

import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import com.bookbookclub.bbc_post_service.feed.exception.FeedErrorCode;
import com.bookbookclub.bbc_post_service.feed.exception.FeedException;
import com.bookbookclub.bbc_post_service.feed.repository.FeedRepository;
import com.bookbookclub.bbc_post_service.global.kafka.FeedEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 피드 등록, 수정, 삭제 등 쓰기 작업을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FeedCommandService {

    private final FeedRepository feedRepository;
    private final FeedEventProducer feedEventProducer;

    /**
     * 피드 생성
     */
    public Long createFeed(Long userId, Long bookId, String content) {
        Feed feed = Feed.create(userId, bookId, content);
        Feed savedFeed = feedRepository.save(feed);
        feedEventProducer.sendFeedCreated(savedFeed);
        return savedFeed.getId();
    }

    /**
     * 피드 내용 수정
     * */
    public void updateFeed(Long feedId, String content) {
        Feed feed = getFeedOrThrow(feedId);
        feed.updateContent(content);
    }

    /**
     * 피드 삭제 (논리 삭제)
     * */
    public void deleteFeed(Long feedId) {
        Feed feed = getFeedOrThrow(feedId);
        feed.delete();
    }

    /**
     * 피드 ID로 조회 (없으면 예외)
     * */
    private Feed getFeedOrThrow(Long feedId) {
        return feedRepository.findByIdAndStatus(feedId, FeedStatus.ACTIVE)
                .orElseThrow(() -> new FeedException(FeedErrorCode.FEED_NOT_FOUND));
    }

}
