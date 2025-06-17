package com.bookbookclub.bbc_post_service.feed.controller;

import com.bookbookclub.bbc_post_service.feed.dto.FeedResponse;
import com.bookbookclub.bbc_post_service.feed.service.FeedQueryService;
import com.bookbookclub.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 피드 조회 관련 API를 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds")
public class FeedQueryController {

    private final FeedQueryService feedQueryService;

    /**
     * 단건 피드 조회 (유저 정보 포함)
     */
    @GetMapping("/{feedId}")
    public ApiResponse<FeedResponse> getFeed(@PathVariable Long feedId) {
        FeedResponse response = feedQueryService.getFeedWithUser(feedId);
        return ApiResponse.success(response);
    }

    /**
     * 전체 피드 목록 조회 (최신순, 유저 정보 포함)
     */
    @GetMapping
    public ApiResponse<List<FeedResponse>> getFeeds(
            @RequestParam(required = false) Long lastFeedId,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<FeedResponse> responses = feedQueryService.getFeedsWithUsers(lastFeedId, size);
        return ApiResponse.success(responses);
    }

    /**
     * 특정 유저의 피드 목록 조회 (유저 정보 포함)
     */
    @GetMapping("/users/{userId}")
    public ApiResponse<List<FeedResponse>> getFeedsByUser(
            @PathVariable Long userId,
            @RequestParam(required = false) Long lastFeedId,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<FeedResponse> responses = feedQueryService.getFeedsWithUserByUserId(lastFeedId, userId, size);
        return ApiResponse.success(responses);
    }

}
