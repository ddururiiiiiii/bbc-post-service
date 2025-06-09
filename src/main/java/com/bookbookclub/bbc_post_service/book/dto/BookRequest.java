package com.bookbookclub.bbc_post_service.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 책 등록 요청 DTO
 */
@Getter
@NoArgsConstructor
public class BookRequest {

    @NotBlank(message = "제목은 비어 있을 수 없습니다.")
    private String title;

    @NotBlank(message = "작가는 비어 있을 수 없습니다.")
    private String author;

    @NotBlank(message = "출판사는 비어 있을 수 없습니다.")
    private String publisher;

    @NotBlank(message = "ISBN은 비어 있을 수 없습니다.")
    private String isbn;

    private String thumbnailUrl;
}
