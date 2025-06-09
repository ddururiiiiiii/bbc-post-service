package com.bookbookclub.bbc_post_service.like.repository;

import java.util.List;

public interface LikeRepositoryCustom {
    List<Long> findFeedIdsByUserId(Long userId);
}