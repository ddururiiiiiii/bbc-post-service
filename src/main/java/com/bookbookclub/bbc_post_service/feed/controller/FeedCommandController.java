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

/**
 * 피드 생성/수정/삭제 등 커맨드성 API를 처리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds")
public class FeedCommandController {

    private final FeedCommandService feedCommandService;

    /**
     * 피드 생성
     * */
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> create(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                    @RequestBody FeedCreateRequest request) {
        Long feedId = feedCommandService.createFeed(
                userDetails.getUserId(),
                request.bookId(),
                request.content()
        );
        return ResponseEntity.ok(ApiResponse.success(feedId));
    }

    /**
     * 피드 수정
     * */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody FeedUpdateRequest request) {
        feedCommandService.updateFeed(userDetails.getUserId(), request.content());
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 피드 삭제
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
        feedCommandService.deleteFeed(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success(null));
    }


}
