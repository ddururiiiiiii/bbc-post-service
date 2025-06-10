package com.bookbookclub.bbc_post_service.feed.controller;

import com.bookbookclub.bbc_post_service.feed.dto.FeedRequest;
import com.bookbookclub.bbc_post_service.feed.dto.FeedResponse;
import com.bookbookclub.bbc_post_service.feed.dto.FeedSimpleResponse;
import com.bookbookclub.bbc_post_service.feed.dto.FeedUpdateRequest;
import com.bookbookclub.bbc_post_service.feed.service.FeedService;
import com.bookbookclub.bbc_post_service.global.security.CustomUserDetails;
import com.bookbookclub.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds")
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<ApiResponse<FeedSimpleResponse>> createFeed(
            @RequestBody @Valid FeedRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        FeedSimpleResponse response = feedService.createFeed(request, userDetails);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 피드 수정
     */
    @PutMapping("/{feedId}")
    public ApiResponse<FeedSimpleResponse> updateFeed(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long feedId,
            @RequestBody @Valid FeedUpdateRequest request
    ) {
        return ApiResponse.success(feedService.updateFeed(userDetails, feedId, request));
    }

    /**
     * 피드 단건 조회
     */
    @GetMapping("/{feedId}")
    public ApiResponse<FeedResponse> getFeed(
            @PathVariable Long feedId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ApiResponse.success(feedService.getFeed(feedId));
    }

    /**
     * 피드 전체 조회 (최신순)
     */
    @GetMapping
    public ApiResponse<List<FeedResponse>> getFeeds(
            Pageable pageable,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ApiResponse.success(feedService.getFeeds(pageable, userDetails.getUserId()));
    }

    /**
     * 특정 유저의 피드 조회
     */
    @GetMapping("/users/{userId}")
    public ApiResponse<List<FeedResponse>> getFeedsByUserId(
            @PathVariable Long userId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ApiResponse.success(feedService.getFeedsByUserId(userId, userDetails.getUserId()));
    }

    /**
     * 피드 삭제
     */
    @DeleteMapping("/{feedId}")
    public ApiResponse<Void> deleteFeed(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long feedId
    ) {
        feedService.deleteFeed(userDetails, feedId);
        return ApiResponse.success(null);
    }
}
