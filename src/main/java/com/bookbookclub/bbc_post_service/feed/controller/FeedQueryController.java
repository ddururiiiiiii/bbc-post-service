package com.bookbookclub.bbc_post_service.feed.controller;

import com.bookbookclub.bbc_post_service.feed.dto.FeedResponse;
import com.bookbookclub.bbc_post_service.feed.entity.Feed;
import com.bookbookclub.bbc_post_service.feed.service.FeedQueryService;
import com.bookbookclub.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds")
public class FeedQueryController {

    private final FeedQueryService feedQueryService;

    /**
     * 피드 단건 조회
     * */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FeedResponse>> getById(@PathVariable Long id) {
        Feed feed = feedQueryService.getVisibleFeedById(id);
        return ResponseEntity.ok(ApiResponse.success(FeedResponse.from(feed)));
    }

    /**
     * 전체 피드 목록 조회 (커서 기반)
     * */
    @GetMapping
    public ResponseEntity<ApiResponse<List<FeedResponse>>> getAll(
            @RequestParam(required = false) Long lastFeedId,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Feed> feeds = feedQueryService.getVisibleFeedsByCursor(lastFeedId, size);
        return ResponseEntity.ok(ApiResponse.success(feeds.stream().map(FeedResponse::from).toList()));
    }

    /**
     * 특정 유저의 피드 목록 조회 (커서 기반)
     * */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<FeedResponse>>> getByUser(
            @PathVariable Long userId,
            @RequestParam(required = false) Long lastFeedId,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Feed> feeds = feedQueryService.getVisibleFeedsByUser(lastFeedId, userId, size);
        return ResponseEntity.ok(ApiResponse.success(feeds.stream().map(FeedResponse::from).toList()));
    }
}
