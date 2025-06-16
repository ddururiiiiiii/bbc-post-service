package com.bookbookclub.bbc_post_service.feed.repository;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 피드 QueryDSL 커스텀 레포지토리 인터페이스
 */
public interface FeedRepositoryCustom {

    List<Feed> findFeedsByUserIdAndCursorWithoutBlinded(Long lastFeedId, Long userId, int size);
    List<Feed> findAllVisibleFeedsByCursor(Long lastFeedId, int size);

}
