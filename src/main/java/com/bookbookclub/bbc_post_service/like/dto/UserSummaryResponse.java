package com.bookbookclub.bbc_post_service.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 요약 응답 DTO
 * - 피드에 좋아요를 누른 사용자 목록 조회 시 사용
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryResponse {

    private Long id;
    private String nickname;
    private String profileImageUrl;

}
