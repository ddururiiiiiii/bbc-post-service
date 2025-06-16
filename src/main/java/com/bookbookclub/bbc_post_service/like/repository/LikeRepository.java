package com.bookbookclub.bbc_post_service.like.repository;


import com.bookbookclub.bbc_post_service.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    /** 특정 피드의 좋아요 수 조회 */
    long countByFeedId(Long feedId);

    /** 특정 사용자의 피드 좋아요 삭제 */
    void deleteByUserIdAndFeedId(Long userId, Long feedId);

    /** 특정 사용자가 이미 해당 피드에 좋아요 했는지 확인 */
    Optional<Like> findByUserIdAndFeedId(Long userId, Long feedId);


}
