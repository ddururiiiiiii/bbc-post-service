package com.bookbookclub.bbc_post_service.like.controller;


import com.bookbookclub.common.security.CustomUserDetails;
import com.bookbookclub.common.dto.UserSummaryResponse;
import com.bookbookclub.bbc_post_service.like.service.LikeService;
import com.bookbookclub.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 좋아요(Like) API 컨트롤러
 */
@RestController
@RequestMapping("/api/feeds/{feedId}/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    /**
     * 피드 좋아요 등록/취소 (토글)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> toggleLike(
            @PathVariable Long feedId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        likeService.toggleLike(feedId, userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 피드 좋아요 수 조회
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getLikeCount(@PathVariable Long feedId) {
        Long likeCount = likeService.getLikeCount(feedId);
        return ResponseEntity.ok(ApiResponse.success(likeCount));
    }

    /**
     * 피드 좋아요한 유저 리스트 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserSummaryResponse>>> getUsersWhoLikedFeed(
            @PathVariable Long feedId
    ) {
        List<UserSummaryResponse> users = likeService.getUsersWhoLikedFeed(feedId);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

}
