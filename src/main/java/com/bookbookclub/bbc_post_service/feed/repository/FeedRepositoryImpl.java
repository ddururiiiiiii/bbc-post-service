package com.bookbookclub.bbc_post_service.feed.repository;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bookbookclub.bbc_post_service.feed.entity.QFeed.feed;


/**
 * 피드 QueryDSL 커스텀 레포지토리 구현체
 */
@Repository
@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Feed> searchFeeds(String keyword, Pageable pageable) {
        return queryFactory
                .selectFrom(feed)
                .where(
                        feed.isBlinded.isFalse()
                                .and(feed.content.containsIgnoreCase(keyword)) // 이제는 content만 검색
                )
                .orderBy(feed.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Feed> findFeedsByUserId(Long userId, Pageable pageable) {
        return queryFactory.selectFrom(feed)
                .where(feed.userId.eq(userId).and(feed.isBlinded.isFalse()))
                .orderBy(feed.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
