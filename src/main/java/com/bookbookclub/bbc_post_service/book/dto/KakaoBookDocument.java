package com.bookbookclub.bbc_post_service.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

/**
 * 카카오 도서 검색 결과 개별 문서
 */
public record KakaoBookDocument(
        @NotBlank String title,
        String contents,
        String url,
        @NotBlank String isbn,
        String datetime,
        List<String> authors,
        String publisher,
        String thumbnail
) {
}