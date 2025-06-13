package com.bookbookclub.bbc_post_service.book.dto;

import lombok.Getter;

import java.util.List;

/**
 * 카카오 책 검색 API 응답 전체를 담는 DTO
 */
@Getter
public class KakaoBookSearchResponse {

    /**
     * 책 정보 목록 (documents 필드에 여러 권이 담김)
     */
    private List<KakaoBookDocument> documents;
}
