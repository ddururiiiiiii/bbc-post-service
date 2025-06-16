package com.bookbookclub.bbc_post_service.feed.repository;


import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.entity.FeedStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 피드 JPA 레포지토리
 */
public interface FeedRepository extends JpaRepository<Feed, Long>, FeedRepositoryCustom {

    Optional<Feed> findByIdAndStatus(Long id, FeedStatus status);
}
