package com.bookbookclub.bbc_post_service.book.dto;

import lombok.Getter;

import java.util.List;

/**
 * 카카오 도서 검색 전체 응답
 */
public record KakaoBookSearchResponse(
        List<KakaoBookDocument> documents
) { }