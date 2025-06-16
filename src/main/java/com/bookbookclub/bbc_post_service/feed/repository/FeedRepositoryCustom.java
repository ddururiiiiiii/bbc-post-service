package com.bookbookclub.bbc_post_service.feed.repository;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 피드 QueryDSL 커스텀 레포지토리 인터페이스
 */
public interface FeedRepositoryCustom {

    /** 특정 유저의 피드 목록 (블라인드 제외, 최신순, 커서 기반) */
    List<Feed> findFeedsByUserIdAndCursorWithoutBlinded(Long lastFeedId, Long userId, int size);

    /** 전체 피드 목록 (블라인드 제외, 최신순, 커서 기반) */
    List<Feed> findAllVisibleFeedsByCursor(Long lastFeedId, int size);

}
