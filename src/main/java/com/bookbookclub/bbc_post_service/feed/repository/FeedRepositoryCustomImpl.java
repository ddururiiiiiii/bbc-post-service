package com.bookbookclub.bbc_post_service.feed.repository;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import com.bookbookclub.bbc_post_service.feed.entity.QFeed;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 * 피드 QueryDSL 커스텀 레포지토리 구현체
 */
public class FeedRepositoryCustomImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public FeedRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 특정 유저의 피드 목록 (블라인드 제외, 최신순, 커서 기반)
     */
    @Override
    public List<Feed> findFeedsByUserIdAndCursorWithoutBlinded(Long lastFeedId, Long userId, int size) {
        QFeed feed = QFeed.feed;

        BooleanBuilder builder = new BooleanBuilder()
                .and(feed.userId.eq(userId))
                .and(feed.status.eq(FeedStatus.ACTIVE));

        if (lastFeedId != null) {
            builder.and(feed.id.lt(lastFeedId));
        }

        return queryFactory.selectFrom(feed)
                .where(builder)
                .orderBy(feed.id.desc())
                .limit(size)
                .fetch();
    }

    /**
     * 전체 피드 목록 (블라인드 제외, 최신순, 커서 기반)
     */
    @Override
    public List<Feed> findAllVisibleFeedsByCursor(Long lastFeedId, int size) {
        QFeed feed = QFeed.feed;

        BooleanBuilder builder = new BooleanBuilder()
                .and(feed.status.eq(FeedStatus.ACTIVE));

        if (lastFeedId != null) {
            builder.and(feed.id.lt(lastFeedId));
        }

        return queryFactory.selectFrom(feed)
                .where(builder)
                .orderBy(feed.id.desc())
                .limit(size)
                .fetch();
    }
}
