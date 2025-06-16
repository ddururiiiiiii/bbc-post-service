package com.bookbookclub.bbc_post_service.like.controller;

import com.bookbookclub.bbc_post_service.like.service.LikeService;
import com.bookbookclub.common.response.ApiResponse;
import com.bookbookclub.common.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feeds/{feedId}/likes")
public class LikeController {

    private final LikeService likeService;

    /**
     * 좋아요 등록
     */
    @PostMapping
    public ApiResponse<Void> like(@PathVariable Long feedId,
                                  @AuthenticationPrincipal CustomUserDetails userDetails) {
        likeService.like(userDetails.getUserId(), feedId);
        return ApiResponse.success("좋아요가 등록되었습니다.");
    }

    /**
     * 좋아요 취소
     */
    @DeleteMapping
    public ApiResponse<Void> unlike(@PathVariable Long feedId,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        likeService.unlike(userDetails.getUserId(), feedId);
        return ApiResponse.success("좋아요가 취소되었습니다.");
    }

    /**
     * 좋아요 수 조회
     */
    @GetMapping("/count")
    public ApiResponse<Long> countLikes(@PathVariable Long feedId) {
        long count = likeService.countLikes(feedId);
        return ApiResponse.success("좋아요 수 조회 완료", count);
    }
}
