package com.bookbookclub.bbc_post_service.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 도서 등록 요청 DTO
 */
public record BookCreateRequest(

        /** ISBN (필수) */
        @NotBlank(message = "ISBN은 필수입니다.")
        String isbn,

        /** 제목 (필수) */
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 200, message = "제목은 200자 이내여야 합니다.")
        String title,

        /** 저자 (필수) */
        @NotBlank(message = "저자는 필수입니다.")
        @Size(max = 100, message = "저자는 100자 이내여야 합니다.")
        String author,

        /** 출판사 (선택) */
        @Size(max = 100, message = "출판사는 100자 이내여야 합니다.")
        String publisher,

        /** 썸네일 URL (선택) */
        String thumbnailUrl

) {}
