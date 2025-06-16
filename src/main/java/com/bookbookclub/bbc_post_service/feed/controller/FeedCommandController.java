package com.bookbookclub.bbc_post_service.feed.controller;

import com.bookbookclub.bbc_post_service.feed.dto.FeedCreateRequest;
import com.bookbookclub.bbc_post_service.feed.dto.FeedUpdateRequest;
import com.bookbookclub.bbc_post_service.feed.service.FeedCommandService;
import com.bookbookclub.common.security.CustomUserDetails;
import com.bookbookclub.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds")
public class FeedCommandController {

    private final FeedCommandService feedCommandService;

    /**
     * 피드 생성
     * */
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> create(@AuthenticationPrincipal CustomUserDetails user,
                                                    @RequestBody FeedCreateRequest request) {
        Long feedId = feedCommandService.createFeed(
                user.getUserId(),
                request.bookId(),
                request.content()
        );
        return ResponseEntity.ok(ApiResponse.success(feedId));
    }

    /**
     * 피드 수정
     * */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody FeedUpdateRequest request) {
        feedCommandService.updateFeed(id, request.content());
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 피드 삭제
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        feedCommandService.deleteFeed(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 피드 블라인드 처리
     * */
    @PatchMapping("/{id}/blind")
    public ResponseEntity<ApiResponse<Void>> blind(@PathVariable Long id) {
        feedCommandService.blindFeed(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
