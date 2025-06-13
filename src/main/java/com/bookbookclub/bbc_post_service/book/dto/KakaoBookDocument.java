package com.bookbookclub.bbc_post_service.book.dto;

import lombok.Getter;

import java.util.List;

/**
 * 카카오 책 검색 API에서 개별 책 정보를 담는 DTO
 */
@Getter
public class KakaoBookDocument {
    private String title;
    private List<String> authors;
    private String publisher;
    private String isbn;
    private String thumbnail;
}
